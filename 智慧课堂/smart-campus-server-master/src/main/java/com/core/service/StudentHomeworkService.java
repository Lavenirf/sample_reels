package com.core.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.StudentHomework;

/**
 * 学生作业信息业务层接口
 *
 * @author zph
 * @date 2023-07-14 19:35:33
 */
public interface StudentHomeworkService {

    /**
     * 查询学生作业信息
     *
     * @param id 学生作业信息主键
     * @return 学生作业信息
     */
     StudentHomework selectStudentHomeworkById(Long id);

    /**
     * 查询学生总数
     */
    Long totalStudent(Long id);

    /**
     * 查询完成作业学生数量
     */
    Long studentCount(Long id);

    /**
     * 分页查询学生作业信息列表
     *
     * @param studentHomework 学生作业信息
     * @return 学生作业信息集合
     */
    IPage<StudentHomework> selectStudentHomeworkPage(StudentHomework studentHomework);

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
    void editStudentHomework(StudentHomework studentHomework);

    /**
     * 修改学生作业信息通过id
     */
    void updateStudentHomework( StudentHomework studentHomework);

    /**
     * 批量删除学生作业信息
     *
     * @param ids 需要删除的学生作业信息主键集合
     * @return 结果
     */
    void deleteStudentHomeworkByIds(Long[] ids);

    /**
     * 删除学生作业信息信息
     *
     * @param id 学生作业信息主键
     * @return 结果
     */
    void deleteStudentHomeworkById(Long id);


}
