package com.core.service.impl;

import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.core.entity.Course;
import com.core.entity.TeacherClazz;
import com.core.mapper.CourseMapper;
import com.core.service.CourseService;
import org.springframework.stereotype.Service;
import com.core.mapper.TeacherMapper;
import com.core.entity.Teacher;
import com.core.service.TeacherService;

/**
 * 教师信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:39
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private CourseMapper courseMapper;

    /**
     * 查询教师信息
     *
     * @param id 教师信息主键
     * @return 教师信息
     */
    @Override
    public Teacher selectTeacherById(Long id) {
        return teacherMapper.selectTeacherById(id);
    }

    /**
     * 分页查询教师信息列表
     *
     * @param teacher 教师信息
     * @return 教师信息
     */
    @Override
    public IPage<Teacher> selectTeacherPage(Teacher teacher) {
        IPage<Teacher> iPage = new Page<>(teacher.getPage(), teacher.getPageSize());
        LambdaQueryWrapper<Teacher> lambdaQueryWrapper = new LambdaQueryWrapper<>(teacher);
        lambdaQueryWrapper.orderByDesc(Teacher::getCreateTime);
        return teacherMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询教师信息列表
     *
     * @param teacher 教师信息
     * @return 教师信息
     */
    @Override
    public List<Teacher> selectTeacherList(Teacher teacher) {
        return teacherMapper.selectTeacherList(teacher);
    }

    /**
     * 查询教师课程列表
     *
     * @param teacherId
     * @return
     */
    @Override
    public List<Course> selectTeacherCourseList(Long teacherId) {
        // 获取教师所教课程
        Teacher teacher = teacherMapper.selectTeacherById(teacherId);
        List<String> courseIdList = Arrays.asList(teacher.getCourseIds().split(","));

        // 查询课程信息
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Course::getId, courseIdList);
        return courseMapper.selectList(lambdaQueryWrapper);
    }

    /**
     * 新增教师信息
     *
     * @param teacher 教师信息
     * @return 结果
     */
    @Override
    public void insertTeacher(Teacher teacher) {
        teacher.setCreateTime(new Date());
        teacherMapper.insertTeacher(teacher);
    }

    /**
     * 修改教师信息
     *
     * @param teacher 教师信息
     * @return 结果
     */
    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }

    /**
     * 批量删除教师信息
     *
     * @param ids 需要删除的教师信息主键
     * @return 结果
     */
    @Override
    public void deleteTeacherByIds(Long[] ids) {
        teacherMapper.deleteTeacherByIds(ids);
    }

    /**
     * 删除教师信息信息
     *
     * @param id 教师信息主键
     * @return 结果
     */
    @Override
    public void deleteTeacherById(Long id) {
        teacherMapper.deleteTeacherById(id);
    }

}
