package com.core.service.impl;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.core.entity.StudentHomework;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.core.mapper.StudentMapper;
import com.core.entity.Student;
import com.core.service.StudentService;

/**
 * 学员信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:33
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    /**
     * 查询学员信息
     *
     * @param id 学员信息主键
     * @return 学员信息
     */
    @Override
    public Student selectStudentById(Long id) {
        return studentMapper.selectStudentById(id);
    }

    /**
     * 分页查询学员信息列表
     *
     * @param student 学员信息
     * @return 学员信息
     */
    @Override
    public IPage<Student> selectStudentPage(Student student) {
        IPage<Student> iPage = new Page<>(student.getPage(), student.getPageSize());
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>(student);
        lambdaQueryWrapper.orderByDesc(Student::getCreateTime);
        return studentMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询学员信息列表
     *
     * @param student 学员信息
     * @return 学员信息
     */
    @Override
    public List<Student> selectStudentList(Student student) {
        return studentMapper.selectStudentList(student);
    }

    /**
     * 随机查询学员信息列表
     *
     * @param student 学员信息
     * @return 学员信息
     */
    @Override
    public List<Student> selectRandomStudentList(Student student) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>(student);
        if (StringUtils.isNotBlank(student.getClazzIds())) {
            lambdaQueryWrapper.in(Student::getClazzId, Arrays.asList(student.getClazzIds().split(",")));
        }
        List<Student> studentList = studentMapper.selectList(lambdaQueryWrapper);
        // 随机排序
        Collections.shuffle(studentList);
        return studentList;
    }

    /**
     * 新增学员信息
     *
     * @param student 学员信息
     * @return 结果
     */
    @Override
    public void insertStudent(Student student) {
        student.setCreateTime(new Date());
        studentMapper.insertStudent(student);
    }

    /**
     * 修改学员信息
     *
     * @param student 学员信息
     * @return 结果
     */
    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学员信息
     *
     * @param ids 需要删除的学员信息主键
     * @return 结果
     */
    @Override
    public void deleteStudentByIds(Long[] ids) {
        studentMapper.deleteStudentByIds(ids);
    }

    /**
     * 删除学员信息信息
     *
     * @param id 学员信息主键
     * @return 结果
     */
    @Override
    public void deleteStudentById(Long id) {
        studentMapper.deleteStudentById(id);
    }

}
