package com.core.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.entity.ClazzRoom;
import com.core.entity.Course;
import com.core.entity.CourseTable;
import com.core.mapper.CourseTableMapper;
import com.core.service.ClazzRoomService;
import com.core.service.CourseService;
import com.core.service.CourseTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 课程表信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
@Service
public class CourseTableServiceImpl implements CourseTableService {

    @Resource
    private CourseTableMapper courseTableMapper;

    @Resource
    private ClazzRoomService clazzRoomService;

    @Resource
    private CourseService courseService;

    /**
     * 查询课程表信息
     *
     * @param id 课程表信息主键
     * @return 课程表信息
     */
    @Override
    public CourseTable selectCourseTableById(Long id) {
        return courseTableMapper.selectCourseTableById(id);
    }

    /**
     * 分页查询课程表信息列表
     *
     * @param courseTable 课程表信息
     * @return 课程表信息
     */
    @Override
    public IPage<CourseTable> selectCourseTablePage(CourseTable courseTable) {
        IPage<CourseTable> iPage = new Page<>(courseTable.getPage(), courseTable.getPageSize());
        LambdaQueryWrapper<CourseTable> lambdaQueryWrapper = new LambdaQueryWrapper<>(courseTable);
        lambdaQueryWrapper.orderByDesc(CourseTable::getCreateTime);
        return courseTableMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询课程表信息列表
     *
     * @param courseTable 课程表信息
     * @return 课程表信息
     */
    @Override
    public List<CourseTable> selectCourseTableList(CourseTable courseTable) {
        return courseTableMapper.selectCourseTableList(courseTable);
    }

    /**
     * 用户查询课程表信息
     *
     * @param courseTable 课程表信息
     * @return 课程表信息集合
     */
    @Override
    public List<CourseTable> selectCourseTableListByUser(CourseTable courseTable) {
        // 查询前端传值开始结束时间内数据
        LambdaQueryWrapper<CourseTable> lambdaQueryWrapper = new LambdaQueryWrapper<>(courseTable);
        if (courseTable.getStartDate() != null) {
            lambdaQueryWrapper.ge(CourseTable::getCourseDate, courseTable.getStartDate());
        }
        if (courseTable.getEndDate() != null) {
            lambdaQueryWrapper.le(CourseTable::getCourseDate, courseTable.getEndDate());
        }
        lambdaQueryWrapper.orderByDesc(CourseTable::getStartSection);
        List<CourseTable> courseTableList = courseTableMapper.selectList(lambdaQueryWrapper);
        if (courseTableList != null) {
            for (CourseTable table : courseTableList) {
                // 教室
                ClazzRoom clazzRoom = clazzRoomService.selectClazzRoomById(table.getClazzRoomId());
                table.setClazzRoomName(clazzRoom.getName());
                // 课程
                Course course = courseService.selectCourseById(table.getCourseId());
                table.setCourseName(course.getName());
                table.setCourseImgUrl(course.getImgUrl());
                // 获取年
                int year = DateUtil.year(table.getCourseDate());
                table.setYear(year);
                // 获取月
                int month = DateUtil.month(table.getCourseDate());
                table.setMonth(month);
                // 获取日
                int day = DateUtil.dayOfMonth(table.getCourseDate());
                table.setDay(day);
                // 获取星期
                int weekDay = DateUtil.dayOfWeek(table.getCourseDate());
                table.setWeekDay(weekDay);
            }
        }
        return courseTableList;
    }

    /**
     * 用户查询今日课程信息
     *
     * @param courseTable 课程表信息
     * @return 课程表信息集合
     */
    @Override
    public List<CourseTable> selectTodayCourseTableListByUser(CourseTable courseTable) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(new Date());
        LambdaQueryWrapper<CourseTable> lambdaQueryWrapper = new LambdaQueryWrapper<>(courseTable);
        lambdaQueryWrapper.eq(CourseTable::getCourseDate, todayDate);
        lambdaQueryWrapper.orderByAsc(CourseTable::getStartSection);
        List<CourseTable> courseTableList = courseTableMapper.selectList(lambdaQueryWrapper);
        if (courseTableList != null) {
            for (CourseTable table : courseTableList) {
                // 教室
                ClazzRoom clazzRoom = clazzRoomService.selectClazzRoomById(table.getClazzRoomId());
                table.setClazzRoomName(clazzRoom.getName());
                // 课程
                Course course = courseService.selectCourseById(table.getCourseId());
                table.setCourseName(course.getName());
                table.setCourseImgUrl(course.getImgUrl());
            }
        }
        return courseTableList;
    }

    /**
     * 新增课程表信息
     *
     * @param courseTable 课程表信息
     * @return 结果
     */
    @Override
    public void insertCourseTable(CourseTable courseTable) {
        courseTable.setCreateTime(new Date());
        courseTableMapper.insertCourseTable(courseTable);
    }

    /**
     * 修改课程表信息
     *
     * @param courseTable 课程表信息
     * @return 结果
     */
    @Override
    public void updateCourseTable(CourseTable courseTable) {
        courseTableMapper.updateCourseTable(courseTable);
    }

    /**
     * 批量删除课程表信息
     *
     * @param ids 需要删除的课程表信息主键
     * @return 结果
     */
    @Override
    public void deleteCourseTableByIds(Long[] ids) {
        courseTableMapper.deleteCourseTableByIds(ids);
    }

    /**
     * 删除课程表信息信息
     *
     * @param id 课程表信息主键
     * @return 结果
     */
    @Override
    public void deleteCourseTableById(Long id) {
        courseTableMapper.deleteCourseTableById(id);
    }

}
