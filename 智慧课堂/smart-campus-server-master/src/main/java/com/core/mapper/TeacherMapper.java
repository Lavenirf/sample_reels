package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.Teacher;

/**
 * 教师信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:39
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    /**
     * 查询教师信息
     *
     * @param id 教师信息主键
     * @return 教师信息
     */
    Teacher selectTeacherById(Long id);

    /**
     * 查询教师信息列表
     *
     * @param teacher 教师信息
     * @return 教师信息集合
     */
    List<Teacher> selectTeacherList(Teacher teacher);

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
     * 删除教师信息
     *
     * @param id 教师信息主键
     * @return 结果
     */
    void deleteTeacherById(Long id);

    /**
     * 批量删除教师信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteTeacherByIds(Long[] ids);
}
