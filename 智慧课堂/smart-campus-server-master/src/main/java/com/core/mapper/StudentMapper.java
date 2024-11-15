package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.Student;

/**
 * 学员信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:33
 */
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 查询学员信息
     *
     * @param id 学员信息主键
     * @return 学员信息
     */
    Student selectStudentById(Long id);

    /**
     * 查询学员信息列表
     *
     * @param student 学员信息
     * @return 学员信息集合
     */
    List<Student> selectStudentList(Student student);

    /**
     * 新增学员信息
     *
     * @param student 学员信息
     * @return 结果
     */
    void insertStudent(Student student);

    /**
     * 修改学员信息
     *
     * @param student 学员信息
     * @return 结果
     */
    void updateStudent(Student student);

    /**
     * 删除学员信息
     *
     * @param id 学员信息主键
     * @return 结果
     */
    void deleteStudentById(Long id);

    /**
     * 批量删除学员信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteStudentByIds(Long[] ids);


}
