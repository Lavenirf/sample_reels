package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.StudentHomework;

/**
 * 学生作业信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:33
 */
public interface StudentHomeworkMapper extends BaseMapper<StudentHomework> {

    /**
     * 查询学生作业信息
     *
     * @param id 学生作业信息主键
     * @return 学生作业信息
     */
    StudentHomework selectStudentHomeworkById(Long id);

    /**
     * 查询学生作业信息列表
     *
     * @param studentHomework 学生作业信息
     * @return 学生作业信息集合
     */
    List<StudentHomework> selectStudentHomeworkList(StudentHomework studentHomework);

    /**
     * 新增学生作业信息
     *
     * @param studentHomework 学生作业信息
     * @return 结果
     */
    void insertStudentHomework(StudentHomework studentHomework);

    /**
     * 修改学生作业信息
     *
     * @param studentHomework 学生作业信息
     * @return 结果
     */
    void updateStudentHomework(StudentHomework studentHomework);

    /**
     * 删除学生作业信息
     *
     * @param id 学生作业信息主键
     * @return 结果
     */
    void deleteStudentHomeworkById(Long id);

    /**
     * 批量删除学生作业信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteStudentHomeworkByIds(Long[] ids);
}
