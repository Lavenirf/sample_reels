package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.Course;

/**
 * 课程信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:29
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 查询课程信息
     *
     * @param id 课程信息主键
     * @return 课程信息
     */
    Course selectCourseById(Long id);

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
     * 删除课程信息
     *
     * @param id 课程信息主键
     * @return 结果
     */
    void deleteCourseById(Long id);

    /**
     * 批量删除课程信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteCourseByIds(Long[] ids);
}
