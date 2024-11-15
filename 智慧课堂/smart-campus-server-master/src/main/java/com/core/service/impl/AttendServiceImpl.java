package com.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.entity.*;
import com.core.exception.ServiceException;
import com.core.mapper.AttendMapper;
import com.core.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 考勤信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:37
 */
@Service
public class AttendServiceImpl implements AttendService {

    private static Logger logger = LoggerFactory.getLogger(AttendService.class);

    @Resource
    private AttendMapper attendMapper;

    @Resource
    private AttendPublishServiceImpl attendPublishService;

    @Resource
    private ClazzRoomService clazzRoomService;

    @Resource
    private ClazzRoomSeatService clazzRoomSeatService;

    @Resource
    private ClazzRoomSeatAttendService clazzRoomSeatAttendService;

    @Resource
    private TeacherService teacherService;

    /**
     * 学生签到
     */
    @Override
    public void sign(Long studentId, Double longitude, Double latitude, Long attendId) {
        logger.info("打卡经度：{}，打卡维度：{}", longitude, latitude);
        Attend attendDb = attendMapper.selectAttendById(attendId);
        // 判断是否已打过卡
        if (!"待打卡".equals(attendDb.getAttendStatus())) {
            // 定时任务已经更新未未打卡
            if ("未打卡".equals(attendDb.getAttendStatus())) {
                throw new ServiceException("已过打卡时间");
            }
            throw new ServiceException("已打过卡");
        }
        Long attendPublishId = attendDb.getAttendPublishId();
        Attend attend = null;
        LambdaUpdateWrapper<Attend> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Attend::getStudentId, studentId).eq(Attend::getAttendPublishId, attendPublishId);
        //获取打卡截至时间，判断学生未打卡，已打卡
        AttendPublish attendPublish = attendPublishService.selectAttendPublishById(attendPublishId);
        Date startTime = attendPublish.getStartTime();
        Date endTime = attendPublish.getEndTime();
        Date nowTime = new Date();
        boolean effectiveDate = isEffectiveDate(nowTime, startTime, endTime);
        //如果effectiveDate=true说明打卡还在进行，可以进行打卡，否则未打卡
        if (effectiveDate) {
            //获得教室对象，从而获取教室经纬度
            ClazzRoom clazzRoom = clazzRoomService.selectClazzRoomById(attendPublish.getClazzRoomId());
            //使用谷歌Jar包，基于 WGS84 距离测算
            double distance = calDistance(latitude, longitude, clazzRoom.getLatitude(), clazzRoom.getLongitude());
            if (distance >= 0 && distance <= 100) {
                //根据学生id和attendPublishId拿到当前考勤表
                attend = new Attend();
                attend.setClockTime(new Date());
                attend.setAttendStatus("已打卡");
                attend.setLatitude(latitude);
                attend.setLongitude(longitude);
                attend.setDistance(distance);
                attendMapper.update(attend, updateWrapper);

                // 查询班级所有座位信息，匹配最优座位
                ClazzRoomSeat clazzRoomSeat = new ClazzRoomSeat();
                clazzRoomSeat.setClazzRoomId(clazzRoom.getId());
                List<ClazzRoomSeat> clazzRoomSeatList = clazzRoomSeatService.selectClazzRoomSeatList(clazzRoomSeat);
                if (CollectionUtils.isNotEmpty(clazzRoomSeatList)) {
                    // 循环座位信息获取距离最近的座位，默认最优座位是第一个
                    ClazzRoomSeat perfectClazzRoomSeat = clazzRoomSeatList.get(0);
                    for (ClazzRoomSeat clazzRoomSeatDb : clazzRoomSeatList) {
                        double calDistance = calDistance(latitude, longitude, clazzRoomSeatDb.getLatitude(), clazzRoomSeatDb.getLongitude());
                        double perfectDistance = calDistance(latitude, longitude, perfectClazzRoomSeat.getLatitude(), perfectClazzRoomSeat.getLongitude());
                        if (calDistance < perfectDistance) {
                            perfectClazzRoomSeat = clazzRoomSeatDb;
                        }
                    }
                    // 新增座位考勤表
                    ClazzRoomSeatAttend clazzRoomSeatAttend = new ClazzRoomSeatAttend();
                    clazzRoomSeatAttend.setClazzRoomId(attendPublish.getClazzRoomId());
                    clazzRoomSeatAttend.setClazzRoomSeatId(perfectClazzRoomSeat.getId());
                    clazzRoomSeatAttend.setStudentId(studentId);
                    clazzRoomSeatAttend.setPublishAttendId(attendDb.getAttendPublishId());
                    clazzRoomSeatAttendService.insertClazzRoomSeatAttend(clazzRoomSeatAttend);
                }
            } else {
                throw new ServiceException("距离打卡点" + (int) distance + "米");
            }
        } else {
           throw new ServiceException("未在打卡时间内");
        }
    }

    /**
     *  计算经纬度实际距离米，使用谷歌Jar包，基于 WGS84 距离测算
     *
     * @param fromLatitude
     * @param fromLongitude
     * @param toLatitude
     * @param toLongitude
     * @return
     */
    private double calDistance(double fromLatitude, double fromLongitude, double toLatitude, double toLongitude) {
        GlobalCoordinates gpsFrom = new GlobalCoordinates(fromLatitude, fromLongitude);
        GlobalCoordinates gpsTo = new GlobalCoordinates(toLatitude, toLongitude);
        GeodeticCurve geodeticCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, gpsFrom, gpsTo);
        return geodeticCurve.getEllipsoidalDistance();
    }

    /**
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询考勤信息
     *
     * @param id 考勤信息主键
     * @return 考勤信息
     */
    @Override
    public Attend selectAttendById(Long id) {
        Attend attend = attendMapper.selectAttendById(id);
        AttendPublish attendPublish = attendPublishService.selectAttendPublishById(attend.getAttendPublishId());
        ClazzRoom clazzRoom = clazzRoomService.selectClazzRoomById(attendPublish.getClazzRoomId());
        attendPublish.setClazzRoomName(clazzRoom.getName());
        attend.setAttendPublish(attendPublish);
        return attend;
    }

    /**
     * 分页查询考勤信息列表
     *
     * @param attend 考勤信息
     * @return 考勤信息
     */
    @Override
    public IPage<Attend> selectAttendPage(Attend attend) {
        IPage<Attend> iPage = new Page<>(attend.getPage(), attend.getPageSize());
        LambdaQueryWrapper<Attend> lambdaQueryWrapper = new LambdaQueryWrapper<>(attend);
        lambdaQueryWrapper.orderByDesc(Attend::getCreateTime);
        IPage<Attend> attendIPage = attendMapper.selectPage(iPage, lambdaQueryWrapper);
        for (Attend attendDb : attendIPage.getRecords()) {
            AttendPublish attendPublish = attendPublishService.selectAttendPublishById(attendDb.getId());
            ClazzRoom clazzRoom = clazzRoomService.selectClazzRoomById(attendPublish.getClazzRoomId());
            attendPublish.setClazzRoomName(clazzRoom.getName());
            attendDb.setAttendPublish(attendPublish);
        }
        return attendIPage;
    }

    /**
     * 查询考勤信息列表
     *
     * @param attend 考勤信息
     * @return 考勤信息
     */
    @Override
    public List<Attend> selectAttendList(Attend attend) {
        //学生查看自己有哪些打卡任务
        List<Attend> attends = attendMapper.selectAttendList(attend);
        for (Attend attendDb : attends) {
            AttendPublish attendPublish = attendPublishService.selectAttendPublishById(attendDb.getAttendPublishId());
            ClazzRoom clazzRoom = clazzRoomService.selectClazzRoomById(attendPublish.getClazzRoomId());
            attendPublish.setClazzRoomName(clazzRoom.getName());
            // 设置教师
            Teacher teacher = teacherService.selectTeacherById(attendPublish.getTeacherId());
            attendPublish.setTeacherName(teacher.getName());
            attendDb.setAttendPublish(attendPublish);
        }
        return attends;
    }

    /**
     * 新增考勤信息
     *
     * @param attend 考勤信息
     * @return 结果
     */
    @Override
    public void insertAttend(Attend attend) {
        attend.setCreateTime(new Date());
        attendMapper.insertAttend(attend);
    }

    /**
     * 修改考勤信息
     *
     * @param attend 考勤信息
     * @return 结果
     */
    @Override
    public void updateAttend(Attend attend) {
        attendMapper.updateAttend(attend);
    }

    /**
     * 批量删除考勤信息
     *
     * @param ids 需要删除的考勤信息主键
     * @return 结果
     */
    @Override
    public void deleteAttendByIds(Long[] ids) {
        attendMapper.deleteAttendByIds(ids);
    }

    /**
     * 删除考勤信息信息
     *
     * @param id 考勤信息主键
     * @return 结果
     */
    @Override
    public void deleteAttendById(Long id) {
        attendMapper.deleteAttendById(id);
    }

}
