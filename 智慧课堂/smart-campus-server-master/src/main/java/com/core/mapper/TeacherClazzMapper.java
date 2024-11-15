package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.TeacherClazz;

/**
 * 教师班级信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:39
 */
public interface TeacherClazzMapper extends BaseMapper<TeacherClazz> {

    /**
     * 查询教师班级信息
     *
     * @param id 教师班级信息主键
     * @return 教师班级信息
     */
    TeacherClazz selectTeacherClazzById(Long id);

    /**
     * 查询教师班级信息列表
     *
     * @param teacherClazz 教师班级信息
     * @return 教师班级信息集合
     */
    List<TeacherClazz> selectTeacherClazzList(TeacherClazz teacherClazz);

    /**
     * 新增教师班级信息
     *
     * @param teacherClazz 教师班级信息
     * @return 结果
     */
    void insertTeacherClazz(TeacherClazz teacherClazz);

    /**
     * 修改教师班级信息
     *
     * @param teacherClazz 教师班级信息
     * @return 结果
     */
    void updateTeacherClazz(TeacherClazz teacherClazz);

    /**
     * 删除教师班级信息
     *
     * @param id 教师班级信息主键
     * @return 结果
     */
    void deleteTeacherClazzById(Long id);

    /**
     * 批量删除教师班级信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteTeacherClazzByIds(Long[] ids);
}
