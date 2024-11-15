package com.core.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.CourseTable;

/**
 * 课程表信息业务层接口
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
public interface CourseTableService {

    /**
     * 查询课程表信息
     *
     * @param id 课程表信息主键
     * @return 课程表信息
     */
     CourseTable selectCourseTableById(Long id);

    /**
     * 分页查询课程表信息列表
     *
     * @param courseTable 课程表信息
     * @return 课程表信息集合
     */
    IPage<CourseTable> selectCourseTablePage(CourseTable courseTable);

    /**
     * 查询课程表信息列表
     *
     * @param courseTable 课程表信息
     * @return 课程表信息集合
     */
    List<CourseTable> selectCourseTableList(CourseTable courseTable);

    /**
     * 用户查询课程表信息
     *
     * @param courseTable 课程表信息
     * @return 课程表信息集合
     */
    List<CourseTable> selectCourseTableListByUser(CourseTable courseTable);

    /**
     * 用户查询今日课程信息
     *
     * @param courseTable 课程表信息
     * @return 课程表信息集合
     */
    List<CourseTable> selectTodayCourseTableListByUser(CourseTable courseTable);

    /**
     * 新增课程表信息
     *
     * @param courseTable 课程表信息
     * @return 结果
     */
    void insertCourseTable(CourseTable courseTable);

    /**
     * 修改课程表信息
     *
     * @param courseTable 课程表信息
     * @return 结果
     */
    void updateCourseTable(CourseTable courseTable);

    /**
     * 批量删除课程表信息
     *
     * @param ids 需要删除的课程表信息主键集合
     * @return 结果
     */
    void deleteCourseTableByIds(Long[] ids);

    /**
     * 删除课程表信息信息
     *
     * @param id 课程表信息主键
     * @return 结果
     */
    void deleteCourseTableById(Long id);

}
