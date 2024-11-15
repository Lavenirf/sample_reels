package com.core.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.Course;
import com.core.entity.Teacher;

/**
 * 教师信息业务层接口
 *
 * @author zph
 * @date 2023-07-14 19:35:39
 */
public interface TeacherService {

    /**
     * 查询教师信息
     *
     * @param id 教师信息主键
     * @return 教师信息
     */
     Teacher selectTeacherById(Long id);

    /**
     * 分页查询教师信息列表
     *
     * @param teacher 教师信息
     * @return 教师信息集合
     */
    IPage<Teacher> selectTeacherPage(Teacher teacher);

    /**
     * 查询教师信息列表
     *
     * @param teacher 教师信息
     * @return 教师信息集合
     */
    List<Teacher> selectTeacherList(Teacher teacher);

    /**
     * 查询教师课程列表
     *
     * @param teacherId
     * @return
     */
    List<Course> selectTeacherCourseList(Long teacherId);

    /**
     * 新增教师信息
     *
     * @param teacher 教师信息
     * @return 结果
     */
    void insertTeacher(Teacher teacher);

    /**
     * 修改教师信息
     *
     * @param teacher 教师信息
     * @return 结果
     */
    void updateTeacher(Teacher teacher);

    /**
     * 批量删除教师信息
     *
     * @param ids 需要删除的教师信息主键集合
     * @return 结果
     */
    void deleteTeacherByIds(Long[] ids);

    /**
     * 删除教师信息信息
     *
     * @param id 教师信息主键
     * @return 结果
     */
    void deleteTeacherById(Long id);


}
