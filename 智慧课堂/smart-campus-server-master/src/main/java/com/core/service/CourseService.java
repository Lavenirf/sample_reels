package com.core.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.Course;

/**
 * 课程信息业务层接口
 *
 * @author zph
 * @date 2023-07-14 19:35:29
 */
public interface CourseService {

    /**
     * 查询课程信息
     *
     * @param id 课程信息主键
     * @return 课程信息
     */
     Course selectCourseById(Long id);

    /**
     * 分页查询课程信息列表
     *
     * @param course 课程信息
     * @return 课程信息集合
     */
    IPage<Course> selectCoursePage(Course course);

    /**
     * 查询课程信息列表
     *
     * @param course 课程信息
     * @return 课程信息集合
     */
    List<Course> selectCourseList(Course course);

    /**
     * 新增课程信息
     *
     * @param course 课程信息
     * @return 结果
     */
    void insertCourse(Course course);

    /**
     * 修改课程信息
     *
     * @param course 课程信息
     * @return 结果
     */
    void updateCourse(Course course);

    /**
     * 批量删除课程信息
     *
     * @param ids 需要删除的课程信息主键集合
     * @return 结果
     */
    void deleteCourseByIds(Long[] ids);

    /**
     * 删除课程信息信息
     *
     * @param id 课程信息主键
     * @return 结果
     */
    void deleteCourseById(Long id);
}
