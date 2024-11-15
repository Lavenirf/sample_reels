package com.core.service.impl;

import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import java.util.List;

import com.core.entity.Clazz;
import org.springframework.stereotype.Service;
import com.core.mapper.CourseMapper;
import com.core.entity.Course;
import com.core.service.CourseService;

/**
 * 课程信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:29
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    /**
     * 查询课程信息
     *
     * @param id 课程信息主键
     * @return 课程信息
     */
    @Override
    public Course selectCourseById(Long id) {
        return courseMapper.selectCourseById(id);
    }

    /**
     * 分页查询课程信息列表
     *
     * @param course 课程信息
     * @return 课程信息
     */
    @Override
    public IPage<Course> selectCoursePage(Course course) {
        IPage<Course> iPage = new Page<>(course.getPage(), course.getPageSize());
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>(course);
        lambdaQueryWrapper.orderByDesc(Course::getCreateTime);
        return courseMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询课程信息列表
     *
     * @param course 课程信息
     * @return 课程信息
     */
    @Override
    public List<Course> selectCourseList(Course course) {
        return courseMapper.selectCourseList(course);
    }

    /**
     * 新增课程信息
     *
     * @param course 课程信息
     * @return 结果
     */
    @Override
    public void insertCourse(Course course) {
        course.setCreateTime(new Date());
        courseMapper.insertCourse(course);
    }

    /**
     * 修改课程信息
     *
     * @param course 课程信息
     * @return 结果
     */
    @Override
    public void updateCourse(Course course) {
        courseMapper.updateCourse(course);
    }

    /**
     * 批量删除课程信息
     *
     * @param ids 需要删除的课程信息主键
     * @return 结果
     */
    @Override
    public void deleteCourseByIds(Long[] ids) {
        courseMapper.deleteCourseByIds(ids);
    }

    /**
     * 删除课程信息信息
     *
     * @param id 课程信息主键
     * @return 结果
     */
    @Override
    public void deleteCourseById(Long id) {
        courseMapper.deleteCourseById(id);
    }

}
