package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.CourseTable;

/**
 * 课程表信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
public interface CourseTableMapper extends BaseMapper<CourseTable> {

    /**
     * 查询课程表信息
     *
     * @param id 课程表信息主键
     * @return 课程表信息
     */
    CourseTable selectCourseTableById(Long id);

    /**
     * 查询课程表信息列表
     *
     * @param courseTable 课程表信息
     * @return 课程表信息集合
     */
    List<CourseTable> selectCourseTableList(CourseTable courseTable);

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
     * 删除课程表信息
     *
     * @param id 课程表信息主键
     * @return 结果
     */
    void deleteCourseTableById(Long id);

    /**
     * 批量删除课程表信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteCourseTableByIds(Long[] ids);
}
