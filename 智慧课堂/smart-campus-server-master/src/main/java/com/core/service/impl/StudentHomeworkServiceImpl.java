package com.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.entity.*;
import com.core.mapper.ClazzMapper;
import com.core.mapper.MessageMapper;
import com.core.mapper.StudentHomeworkMapper;
import com.core.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 学生作业信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:33
 */
@Service
public class StudentHomeworkServiceImpl implements StudentHomeworkService {

    @Resource
    private StudentHomeworkMapper studentHomeworkMapper;

    @Resource
    private HomeworkService homeworkService;

    @Resource
    private ClazzMapper clazzMapper;

    @Resource
    private TeacherService teacherService;

    @Resource
    private StudentService studentService;

    @Resource
    private CourseService courseService;

    @Resource
    private MessageMapper messageMapper;

    /**
     * 查询学生作业信息
     *
     * @param id 学生作业信息主键
     * @return 学生作业信息
     */
    @Override
    public StudentHomework selectStudentHomeworkById(Long id) {
        StudentHomework studentHomeworkDb = studentHomeworkMapper.selectStudentHomeworkById(id);
        // 学员名称
        String studentName = studentService.selectStudentById(studentHomeworkDb.getStudentId()).getName();
        studentHomeworkDb.setStudentName(studentName);
        // 作业信息
        Homework homework = homeworkService.selectHomeworkById(studentHomeworkDb.getHomeworkId());
        // 班级名称
        LambdaQueryWrapper<Clazz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Clazz::getId, Arrays.asList(homework.getClazzIds().split(",")));
        List<Clazz> clazzList = clazzMapper.selectList(lambdaQueryWrapper);
        String clazzNames = clazzList.stream().map(Clazz::getName).collect(Collectors.joining(","));
        homework.setClazzNames(clazzNames);
        // 课程名称
        String courseName = courseService.selectCourseById(homework.getCourseId()).getName();
        homework.setCourseName(courseName);
        // 教师名称
        String teacherName = teacherService.selectTeacherById(homework.getTeacherId()).getName();
        homework.setTeacherName(teacherName);
        studentHomeworkDb.setHomework(homework);
        return studentHomeworkDb;
    }

    /**
     * 查询学生数量
     */
    @Override
    public Long totalStudent(Long id) {
        LambdaQueryWrapper<StudentHomework> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StudentHomework::getHomeworkId,id);
        return studentHomeworkMapper.selectCount(lambdaQueryWrapper);
    }


    /**
     * 查询学生已完成数量
     */
    @Override
    public Long studentCount(Long id) {
        LambdaQueryWrapper<StudentHomework> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StudentHomework::getHomeworkId,id).eq(StudentHomework::getStatus,"已上传");
        return studentHomeworkMapper.selectCount(lambdaQueryWrapper);

    }

    /**
     * 分页查询学生作业信息列表
     *
     * @param studentHomework 学生作业信息
     * @return 学生作业信息
     */
    @Override
    public IPage<StudentHomework> selectStudentHomeworkPage(StudentHomework studentHomework) {
        IPage<StudentHomework> iPage = new Page<>(studentHomework.getPage(), studentHomework.getPageSize());
        LambdaQueryWrapper<StudentHomework> lambdaQueryWrapper = new LambdaQueryWrapper<>(studentHomework);
        lambdaQueryWrapper.orderByDesc(StudentHomework::getCreateTime);
        IPage<StudentHomework> studentHomeworkIPage = studentHomeworkMapper.selectPage(iPage, lambdaQueryWrapper);
        for (StudentHomework studentHomeworkDb : studentHomeworkIPage.getRecords()) {
            // 学员名称
            String studentName = studentService.selectStudentById(studentHomeworkDb.getStudentId()).getName();
            studentHomeworkDb.setStudentName(studentName);
            // 作业信息
            Homework homework = homeworkService.selectHomeworkById(studentHomeworkDb.getHomeworkId());
            // 班级名称
            LambdaQueryWrapper<Clazz> clazzLambdaQueryWrapper = new LambdaQueryWrapper<>();
            clazzLambdaQueryWrapper.in(Clazz::getId, Arrays.asList(homework.getClazzIds().split(",")));
            List<Clazz> clazzList = clazzMapper.selectList(clazzLambdaQueryWrapper);
            String clazzNames = clazzList.stream().map(Clazz::getName).collect(Collectors.joining(","));
            homework.setClazzNames(clazzNames);
            // 课程名称
            String courseName = courseService.selectCourseById(homework.getCourseId()).getName();
            homework.setCourseName(courseName);
            // 教师名称
            String teacherName = teacherService.selectTeacherById(homework.getTeacherId()).getName();
            homework.setTeacherName(teacherName);
            studentHomeworkDb.setHomework(homework);
        }
        return studentHomeworkIPage;
    }

    /**
     * 查询学生作业信息列表
     *
     * @param studentHomework 学生作业信息
     * @return 学生作业信息
     */
    @Override
    public List<StudentHomework> selectStudentHomeworkList(StudentHomework studentHomework) {
        //学生查询有那些作业，,通过前端传过来studentHomework对象，通过对象里面student_id来找到自己有哪些作业,返回一个作业studentHomework集合，
        // 通过集合中homeworkid找到学生有哪些作业，封装在homework对象中，一起返回给前端
        List<StudentHomework> studentHomeworkList = studentHomeworkMapper.selectStudentHomeworkList(studentHomework);
        for (StudentHomework studentHomeworkDb : studentHomeworkList) {
            // 学员名称
            String studentName = studentService.selectStudentById(studentHomeworkDb.getStudentId()).getName();
            studentHomeworkDb.setStudentName(studentName);
            // 作业信息
            Homework homework = homeworkService.selectHomeworkById(studentHomeworkDb.getHomeworkId());
            // 班级名称
            LambdaQueryWrapper<Clazz> clazzLambdaQueryWrapper = new LambdaQueryWrapper<>();
            clazzLambdaQueryWrapper.in(Clazz::getId, Arrays.asList(homework.getClazzIds().split(",")));
            List<Clazz> clazzList = clazzMapper.selectList(clazzLambdaQueryWrapper);
            String clazzNames = clazzList.stream().map(Clazz::getName).collect(Collectors.joining(","));
            homework.setClazzNames(clazzNames);
            // 课程名称
            String courseName = courseService.selectCourseById(homework.getCourseId()).getName();
            homework.setCourseName(courseName);
            // 教师名称
            String teacherName = teacherService.selectTeacherById(homework.getTeacherId()).getName();
            homework.setTeacherName(teacherName);
            studentHomeworkDb.setHomework(homework);
        }
        return studentHomeworkList;
    }

    /**
     * 新增学生作业信息
     *
     * @param studentHomework 学生作业信息
     * @return 结果
     */
    @Override
    public void insertStudentHomework(StudentHomework studentHomework) {
        studentHomework.setCreateTime(new Date());
        studentHomeworkMapper.insertStudentHomework(studentHomework);
    }

    /**
     * 修改学生作业信息
     *
     * @param studentHomework 学生作业信息
     * @return 结果
     */
    @Override
    public void editStudentHomework(StudentHomework studentHomework) {
        studentHomework.setStatus("已批改");
        studentHomeworkMapper.updateStudentHomework(studentHomework);
    }


    /**
     * 通过id修改信息
     */
    @Override
    public void updateStudentHomework(StudentHomework studentHomework) {
        LambdaUpdateWrapper<StudentHomework> lambdaQueryWrapper = new LambdaUpdateWrapper<>();
        lambdaQueryWrapper.eq(StudentHomework::getId, studentHomework.getId());
        StudentHomework studentHomeworkInfo = new StudentHomework();
        studentHomeworkInfo.setStatus("已上传");
        studentHomeworkInfo.setUploadTime(new Date());
        studentHomeworkInfo.setFileUrl(studentHomework.getFileUrl());
        studentHomeworkMapper.update(studentHomeworkInfo, lambdaQueryWrapper);

        StudentHomework studentHomeworkDb = studentHomeworkMapper.selectStudentHomeworkById(studentHomework.getId());
        Homework homeworkDb = homeworkService.selectHomeworkById(studentHomeworkDb.getHomeworkId());
        Teacher teacherDb = teacherService.selectTeacherById(homeworkDb.getTeacherId());
        Student studentDb = studentService.selectStudentById(studentHomeworkDb.getStudentId());
        //通知教师作业已上传
        Message message = new Message();
        message.setUserId(teacherDb.getUserId());
        message.setType("消息");
        message.setTitle("作业消息");
        message.setBussId(studentHomeworkDb.getId());
        message.setSubType("作业");
        message.setContent(studentDb.getName() + "，已完成并上传" + homeworkDb.getName() + "作业，请及时批改！");
        message.setIsRead("未读");
        message.setSendTime(new Date());
        message.setCreateTime(new Date());
        messageMapper.insertMessage(message);
    }

    /**
     * 批量删除学生作业信息
     *
     * @param ids 需要删除的学生作业信息主键
     * @return 结果
     */
    @Override
    public void deleteStudentHomeworkByIds(Long[] ids) {
        studentHomeworkMapper.deleteStudentHomeworkByIds(ids);
    }

    /**
     * 删除学生作业信息信息
     *
     * @param id 学生作业信息主键
     * @return 结果
     */
    @Override
    public void deleteStudentHomeworkById(Long id) {
        studentHomeworkMapper.deleteStudentHomeworkById(id);
    }

}
