package com.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.entity.*;
import com.core.mapper.AttendMapper;
import com.core.mapper.AttendPublishMapper;
import com.core.mapper.ClazzMapper;
import com.core.mapper.MessageMapper;
import com.core.service.AttendPublishService;
import com.core.service.AttendService;
import com.core.service.ClazzRoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 教师发布考勤信息业务层处理
 *
 * @author zph
 * @date 2023-07-17 12:35:57
 */
@Service
public class AttendPublishServiceImpl implements AttendPublishService {

    @Resource
    private AttendPublishMapper attendPublishMapper;

    @Resource
    private StudentServiceImpl studentService;

    @Resource
    private AttendService attendService;

    @Resource
    private ClazzRoomService clazzRoomService;

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private ClazzMapper clazzMapper;

    @Resource
    private AttendMapper attendMapper;

    /**
     * 查询教师发布考勤信息
     *
     * @param id 教师发布考勤信息主键
     * @return 教师发布考勤信息
     */
    @Override
    public AttendPublish selectAttendPublishById(Long id) {
        AttendPublish attendPublish = attendPublishMapper.selectAttendPublishById(id);
        ClazzRoom clazzRoom = clazzRoomService.selectClazzRoomById(attendPublish.getClazzRoomId());
        attendPublish.setClazzRoom(clazzRoom);
        // 班级名称
        LambdaQueryWrapper<Clazz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Clazz::getId, Arrays.asList(attendPublish.getClazzIds().split(",")));
        List<Clazz> clazzList = clazzMapper.selectList(lambdaQueryWrapper);
        String clazzNames = clazzList.stream().map(Clazz::getName).collect(Collectors.joining(","));
        attendPublish.setClazzNames(clazzNames);
        return attendPublish;
    }

    /**
     * 分页查询教师发布考勤信息列表
     *
     * @param attendPublish 教师发布考勤信息
     * @return 教师发布考勤信息
     */
    @Override
    public IPage<AttendPublish> selectAttendPublishPage(AttendPublish attendPublish) {
        IPage<AttendPublish> iPage = new Page<>(attendPublish.getPage(), attendPublish.getPageSize());
        LambdaQueryWrapper<AttendPublish> lambdaQueryWrapper = new LambdaQueryWrapper<>(attendPublish);
        lambdaQueryWrapper.orderByDesc(AttendPublish::getCreateTime);
        return attendPublishMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询教师发布考勤信息列表
     *
     * @param attendPublish 教师发布考勤信息
     * @return 教师发布考勤信息
     */
    @Override
    public List<AttendPublish> selectAttendPublishList(AttendPublish attendPublish) {
        List<AttendPublish> attendPublishes = attendPublishMapper.selectAttendPublishList(attendPublish);
        for (AttendPublish attendPublishDb : attendPublishes) {
            ClazzRoom clazzRoom = clazzRoomService.selectClazzRoomById(attendPublishDb.getClazzRoomId());
            attendPublishDb.setClazzRoomName(clazzRoom.getName());
        }
        return attendPublishes;
    }


    /**
     * 计时器更新未打卡
     */
    public void timeAttend(Date endTime, Long attendPublishId) {
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            public void run() {
                Attend attend = new Attend();
                attend.setAttendPublishId(attendPublishId);
                List<Attend> attends = attendService.selectAttendList(attend);
                for (Attend attendDb : attends) {
                    if ("待打卡".equals(attendDb.getAttendStatus())) {
                        LambdaUpdateWrapper<Attend> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                        lambdaUpdateWrapper.eq(Attend::getAttendPublishId, attendPublishId);
                        Attend attendInfo = new Attend();
                        attendInfo.setAttendStatus("未打卡");
                        attendMapper.update(attendInfo, lambdaUpdateWrapper);
                    }
                }
            }
        };
        timer.schedule(task, endTime);
    }

    /**
     * 新增教师发布考勤信息
     *
     * @param attendPublish 教师发布考勤信息
     * @return 结果
     */
    @Override
    public void insertAttendPublish(AttendPublish attendPublish) throws Exception {
        attendPublish.setPublishTime(new Date());
        attendPublish.setCreateTime(new Date());
        attendPublishMapper.insertAttendPublish(attendPublish);
        //将字符串转成字符串数组
        String[] classIdList = attendPublish.getClazzIds().split(",");
        //将字符串数组转成整型数组
        long[] longClassIdList = Arrays.stream(classIdList).mapToLong(Long::parseLong).toArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 0; i < longClassIdList.length; i++) {
            Student student = new Student();
            student.setClazzId(longClassIdList[i]);
            List<Student> students = studentService.selectStudentList(student);
            //获取每个班需要打卡学生信息，把学生id存入学生考勤表中
            for (Student studentDb : students) {
                Attend attend = new Attend();
                attend.setStudentId(studentDb.getId());
                attend.setAttendPublishId(attendPublish.getId());
                attend.setAttendStatus("待打卡");
                attendService.insertAttend(attend);

                //通知学生有打卡任务
                Message message = new Message();
                message.setUserId(studentDb.getUserId());
                message.setType("消息");
                message.setTitle("打卡消息");
                message.setBussId(attend.getId());
                message.setSubType("考勤");
                message.setContent(studentDb.getName() + "，你有一份打卡任务，将于" + dateFormat.format(attendPublish.getEndTime()) + "结束，请在规定时间内完成！");
                message.setIsRead("未读");
                message.setSendTime(new Date());
                message.setCreateTime(new Date());
                messageMapper.insertMessage(message);
            }
        }
        //计时器
        timeAttend(attendPublish.getEndTime(), attendPublish.getId());
    }

    /**
     * 修改教师发布考勤信息
     *
     * @param attendPublish 教师发布考勤信息
     * @return 结果
     */
    @Override
    public void updateAttendPublish(AttendPublish attendPublish) {
        attendPublishMapper.updateAttendPublish(attendPublish);
    }

    /**
     * 批量删除教师发布考勤信息
     *
     * @param ids 需要删除的教师发布考勤信息主键
     * @return 结果
     */
    @Override
    public void deleteAttendPublishByIds(Long[] ids) {
        attendPublishMapper.deleteAttendPublishByIds(ids);
    }

    /**
     * 删除教师发布考勤信息信息
     *
     * @param id 教师发布考勤信息主键
     * @return 结果
     */
    @Override
    public void deleteAttendPublishById(Long id) {
        attendPublishMapper.deleteAttendPublishById(id);
    }

}
