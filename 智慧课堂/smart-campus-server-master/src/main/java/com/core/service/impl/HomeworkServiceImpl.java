package com.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.entity.*;
import com.core.mapper.ClazzMapper;
import com.core.mapper.HomeworkMapper;
import com.core.mapper.MessageMapper;
import com.core.mapper.StudentMapper;
import com.core.service.CourseService;
import com.core.service.HomeworkService;
import com.core.service.StudentHomeworkService;
import com.core.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 作业信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Resource
    private HomeworkMapper homeworkMapper;

    @Resource
    private StudentHomeworkService studentHomeworkService;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private ClazzMapper clazzMapper;

    @Resource
    private TeacherService teacherService;

    @Resource
    private CourseService courseService;


    /**
     * 查询作业信息
     *
     * @param id 作业信息主键
     * @return 作业信息
     */
    @Override
    public Homework selectHomeworkById(Long id) {
        return homeworkMapper.selectHomeworkById(id);
    }

    /**
     * 分页查询作业信息列表
     *
     * @param homework 作业信息
     * @return 作业信息
     */
    @Override
    public IPage<Homework> selectHomeworkPage(Homework homework) {
        IPage<Homework> iPage = new Page<>(homework.getPage(), homework.getPageSize());
        LambdaQueryWrapper<Homework> lambdaQueryWrapper = new LambdaQueryWrapper<>(homework);
        lambdaQueryWrapper.orderByDesc(Homework::getCreateTime);
        return homeworkMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询作业信息列表
     *
     * @param homework 作业信息
     * @return 作业信息
     */
    @Override
    public List<Homework> selectHomeworkList(Homework homework) {
        List<Homework> homeworkList = homeworkMapper.selectHomeworkList(homework);
        for (Homework homeworkDb : homeworkList) {
            Long count = studentHomeworkService.studentCount(homeworkDb.getId());
            Long totalStudent = studentHomeworkService.totalStudent(homeworkDb.getId());
            homeworkDb.setCount(count);
            homeworkDb.setTotalStudent(totalStudent);
            // 班级名称
            LambdaQueryWrapper<Clazz> clazzLambdaQueryWrapper = new LambdaQueryWrapper<>();
            clazzLambdaQueryWrapper.in(Clazz::getId, Arrays.asList(homeworkDb.getClazzIds().split(",")));
            List<Clazz> clazzList = clazzMapper.selectList(clazzLambdaQueryWrapper);
            String clazzNames = clazzList.stream().map(Clazz::getName).collect(Collectors.joining(","));
            homeworkDb.setClazzNames(clazzNames);
            // 教师名称
            String teacherName = teacherService.selectTeacherById(homeworkDb.getTeacherId()).getName();
            homeworkDb.setTeacherName(teacherName);
            // 课程名称
            String courseName = courseService.selectCourseById(homeworkDb.getCourseId()).getName();
            homeworkDb.setCourseName(courseName);
        }
        return homeworkList;
    }

    /**
     * 新增作业信息
     *
     * @param homework 作业信息
     * @return 结果
     */
    @Override
    public void insertHomework(Homework homework) {
        // 新增作业信息
        homework.setCreateTime(new Date());
        homeworkMapper.insertHomework(homework);
        // 获取所有需要上传作业学生
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.in(Student::getClazzId, Arrays.asList(homework.getClazzIds().split(",")));
        List<Student> students = studentMapper.selectList(studentLambdaQueryWrapper);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Student studentDb : students) {
            // 循环所有学生信息，插入到学生作业表中
            StudentHomework studentHomework = new StudentHomework();
            studentHomework.setStudentId(studentDb.getId());
            studentHomework.setHomeworkId(homework.getId());
            studentHomework.setStatus("未上传");
            studentHomeworkService.insertStudentHomework(studentHomework);
            //循环所有学生信息，给学生发布发布消息通知
            Message message = new Message();
            message.setUserId(studentDb.getUserId());
            message.setType("消息");
            message.setTitle("作业消息");
            message.setSubType("作业");
            message.setBussId(studentHomework.getId());
            message.setContent(studentDb.getName() + "，你有一份作业，将于" + dateFormat.format(homework.getEndDate()) + "结束，请按时完成！");
            message.setIsRead("未读");
            message.setSendTime(new Date());
            message.setCreateTime(new Date());
            messageMapper.insertMessage(message);
        }

    }

    /**
     * 修改作业信息
     *
     * @param homework 作业信息
     * @return 结果
     */
    @Override
    public void updateHomework(Homework homework) {
        homeworkMapper.updateHomework(homework);
    }

    /**
     * 批量删除作业信息
     *
     * @param ids 需要删除的作业信息主键
     * @return 结果
     */
    @Override
    public void deleteHomeworkByIds(Long[] ids) {
        homeworkMapper.deleteHomeworkByIds(ids);
    }

    /**
     * 删除作业信息信息
     *
     * @param id 作业信息主键
     * @return 结果
     */
    @Override
    public void deleteHomeworkById(Long id) {
        homeworkMapper.deleteHomeworkById(id);
    }

}
