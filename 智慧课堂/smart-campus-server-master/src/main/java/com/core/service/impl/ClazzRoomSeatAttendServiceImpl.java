package com.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.entity.ClazzRoomSeat;
import com.core.entity.ClazzRoomSeatAttend;
import com.core.entity.Student;
import com.core.mapper.ClazzRoomSeatAttendMapper;
import com.core.service.ClazzRoomSeatAttendService;
import com.core.service.ClazzRoomSeatService;
import com.core.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学员考勤座位信息业务层处理
 *
 * @author zjp
 * @date 2023-07-25 20:17:00
 */
@Service
public class ClazzRoomSeatAttendServiceImpl implements ClazzRoomSeatAttendService {

    @Resource
    private StudentService studentService;

    @Resource
    private ClazzRoomSeatService clazzRoomSeatService;

    @Resource
    private ClazzRoomSeatAttendMapper clazzRoomSeatAttendMapper;

    /**
     * 查询学员考勤座位信息
     *
     * @param id 学员考勤座位信息主键
     * @return 学员考勤座位信息
     */
    @Override
    public ClazzRoomSeatAttend selectClazzRoomSeatAttendById(Long id) {
        return clazzRoomSeatAttendMapper.selectClazzRoomSeatAttendById(id);
    }

    /**
     * 分页查询学员考勤座位信息列表
     *
     * @param clazzRoomSeatAttend 学员考勤座位信息
     * @return 学员考勤座位信息
     */
    @Override
    public IPage<ClazzRoomSeatAttend> selectClazzRoomSeatAttendPage(ClazzRoomSeatAttend clazzRoomSeatAttend) {
        IPage<ClazzRoomSeatAttend> iPage = new Page<>(clazzRoomSeatAttend.getPage(), clazzRoomSeatAttend.getPageSize());
        LambdaQueryWrapper<ClazzRoomSeatAttend> lambdaQueryWrapper = new LambdaQueryWrapper<>(clazzRoomSeatAttend);
        lambdaQueryWrapper.orderByDesc(ClazzRoomSeatAttend::getCreateTime);
        return clazzRoomSeatAttendMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询学员考勤座位信息列表
     *
     * @param clazzRoomSeatAttend 学员考勤座位信息
     * @return 学员考勤座位信息
     */
    @Override
    public List<ClazzRoomSeatAttend> selectClazzRoomSeatAttendList(ClazzRoomSeatAttend clazzRoomSeatAttend) {
        return clazzRoomSeatAttendMapper.selectClazzRoomSeatAttendList(clazzRoomSeatAttend);
    }

    /**
     * 查询学员考勤座位信息列表Map
     *
     * @param clazzRoomSeatAttend
     * @return
     */
    @Override
    public Map<Long, ClazzRoomSeatAttend> selectClazzRoomSeatAttendMap(ClazzRoomSeatAttend clazzRoomSeatAttend) {
        Map<Long, ClazzRoomSeatAttend> resultMap = new HashMap<>();
        List<ClazzRoomSeatAttend> clazzRoomSeatAttends = clazzRoomSeatAttendMapper.selectClazzRoomSeatAttendList(clazzRoomSeatAttend);
        for (ClazzRoomSeatAttend clazzRoomSeatAttendDb : clazzRoomSeatAttends) {
            // 获取学生姓名
            Student studentDb = studentService.selectStudentById(clazzRoomSeatAttendDb.getStudentId());
            clazzRoomSeatAttendDb.setStudentName(studentDb.getName());
            // 获取座位信息
            ClazzRoomSeat clazzRoomSeatDb = clazzRoomSeatService.selectClazzRoomSeatById(clazzRoomSeatAttendDb.getClazzRoomSeatId());
            clazzRoomSeatAttendDb.setClazzRoomSeat(clazzRoomSeatDb);
            resultMap.put(clazzRoomSeatDb.getId(), clazzRoomSeatAttendDb);
        }
        return resultMap;
    }

    /**
     * 新增学员考勤座位信息
     *
     * @param clazzRoomSeatAttend 学员考勤座位信息
     * @return 结果
     */
    @Override
    public void insertClazzRoomSeatAttend(ClazzRoomSeatAttend clazzRoomSeatAttend) {
        clazzRoomSeatAttend.setCreateTime(new Date());
        clazzRoomSeatAttendMapper.insertClazzRoomSeatAttend(clazzRoomSeatAttend);
    }

    /**
     * 修改学员考勤座位信息
     *
     * @param clazzRoomSeatAttend 学员考勤座位信息
     * @return 结果
     */
    @Override
    public void updateClazzRoomSeatAttend(ClazzRoomSeatAttend clazzRoomSeatAttend) {
        clazzRoomSeatAttendMapper.updateClazzRoomSeatAttend(clazzRoomSeatAttend);
    }

    /**
     * 批量删除学员考勤座位信息
     *
     * @param ids 需要删除的学员考勤座位信息主键
     * @return 结果
     */
    @Override
    public void deleteClazzRoomSeatAttendByIds(Long[] ids) {
        clazzRoomSeatAttendMapper.deleteClazzRoomSeatAttendByIds(ids);
    }

    /**
     * 删除学员考勤座位信息信息
     *
     * @param id 学员考勤座位信息主键
     * @return 结果
     */
    @Override
    public void deleteClazzRoomSeatAttendById(Long id) {
        clazzRoomSeatAttendMapper.deleteClazzRoomSeatAttendById(id);
    }

}
