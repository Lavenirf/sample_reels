package com.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.entity.*;
import com.core.mapper.*;
import com.core.service.CourseService;
import com.core.service.ExamService;
import com.core.service.TeacherService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 考试信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Resource
    private ExamMapper examMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ScoreMapper scoreService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private ClazzMapper clazzMapper;

    @Resource
    private CourseService courseService;


    /**
     * 查询考试信息
     *
     * @param id 考试信息主键
     * @return 考试信息
     */
    @Override
    public Exam selectExamById(Long id) {
        Exam exam = examMapper.selectExamById(id);
        LambdaQueryWrapper<Clazz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Clazz::getId, Arrays.asList(exam.getClazzIds().split(",")));
        List<Clazz> clazzeList = clazzMapper.selectList(lambdaQueryWrapper);
        String clazzName = clazzeList.stream().map(Clazz::getName).collect(Collectors.joining(","));
        exam.setClazzNames(clazzName);
        //课程
        Course course = courseService.selectCourseById(exam.getCourseId());
        exam.setCourse(course);
        return exam;
    }

    /**
     * 分页查询考试信息列表
     *
     * @param exam 考试信息
     * @return 考试信息
     */
    @Override
    public IPage<Exam> selectExamPage(Exam exam) {
        IPage<Exam> iPage = new Page<>(exam.getPage(), exam.getPageSize());
        LambdaQueryWrapper<Exam> lambdaQueryWrapper = new LambdaQueryWrapper<>(exam);
        lambdaQueryWrapper.orderByDesc(Exam::getCreateTime);
        IPage<Exam> examIPage = examMapper.selectPage(iPage, lambdaQueryWrapper);
        for (Exam examData : examIPage.getRecords()) {
            //考试班级
            LambdaQueryWrapper<Clazz> clazzQueryWrapper = new LambdaQueryWrapper<>();
            clazzQueryWrapper.in(Clazz::getId, Arrays.asList(exam.getClazzIds().split(",")));
            List<Clazz> clazzeList = clazzMapper.selectList(clazzQueryWrapper);
            String clazzName = clazzeList.stream().map(Clazz::getName).collect(Collectors.joining(","));
            exam.setClazzNames(clazzName);
            //课程
            Course course = courseService.selectCourseById(examData.getCourseId());
            examData.setCourse(course);
        }
        return examIPage;
    }

    @Override
    public List<Exam> studentExamList(Long studentId) {
        // 根据学员成绩表获取所有考试的主键
        Score score = new Score();
        score.setStudentId(studentId);
        List<Score> scoreList = scoreService.selectScoreList(score);
        if (CollectionUtils.isNotEmpty(scoreList)) {
            List<Long> examIdList = scoreList.stream().map(Score::getExamId).collect(Collectors.toList());
            LambdaQueryWrapper<Exam> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(Exam::getId, examIdList);
            lambdaQueryWrapper.orderByDesc(Exam::getCreateTime);
            List<Exam> exams = examMapper.selectList(lambdaQueryWrapper);
            //获取每个学生考试信息考试班级
            for (Exam examData : exams) {
                //考试班级
                LambdaQueryWrapper<Clazz> clazzQueryWrapper = new LambdaQueryWrapper<>();
                clazzQueryWrapper.in(Clazz::getId, Arrays.asList(examData.getClazzIds().split(",")));
                List<Clazz> clazzeList = clazzMapper.selectList(clazzQueryWrapper);
                String clazzName = clazzeList.stream().map(Clazz::getName).collect(Collectors.joining(","));
                examData.setClazzNames(clazzName);
                //课程
                Course course = courseService.selectCourseById(examData.getCourseId());
                examData.setCourse(course);
                //教师
                Teacher teacher = teacherService.selectTeacherById(examData.getTeacherId());
                examData.setTeacherName(teacher.getName());
            }
            return exams;
        }
        return new ArrayList<>();
    }

    /**
     * 查询考试信息列表
     *
     * @param exam 考试信息
     * @return 考试信息
     */
    @Override
    public List<Exam> selectExamList(Exam exam) {
        List<Exam> exams = examMapper.selectExamList(exam);
        //获取每个学生考试信息考试地址
        for (Exam examData : exams) {
            //考试班级
            LambdaQueryWrapper<Clazz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(Clazz::getId, Arrays.asList(examData.getClazzIds()));
            List<Clazz> clazzeList = clazzMapper.selectList(lambdaQueryWrapper);
            String clazzNames = clazzeList.stream().map(Clazz::getName).collect(Collectors.joining(","));
            examData.setClazzNames(clazzNames);
            //课程
            Course course = courseService.selectCourseById(examData.getCourseId());
            examData.setCourse(course);
        }
        return exams;
    }

    /**
     * 新增考试信息
     *
     * @param exam 考试信息
     * @return 结果
     */
    @Override
    public void insertExam(Exam exam) {
        exam.setCreateTime(new Date());
        examMapper.insertExam(exam);
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Student::getClazzId, Arrays.asList(exam.getClazzIds().split(",")));
        List<Student> students = studentMapper.selectList(lambdaQueryWrapper);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Student studentDb : students) {
            Score score = new Score();
            score.setClazzId(studentDb.getClazzId());
            score.setStudentId(studentDb.getId());
            score.setExamId(exam.getId());
            score.setStatus("未上传");
            score.setCreateTime(new Date());
            scoreService.insertScore(score);

            //发布考试消息通知
            Message message = new Message();
            message.setUserId(studentDb.getUserId());
            message.setType("消息");
            message.setTitle("考试消息");
            message.setSubType("考试");
            message.setBussId(score.getId());
            message.setContent(studentDb.getName() + "，你有一场考试，将在" + dateFormat.format(exam.getExamTime()) + "进行，请准时参加！");
            message.setIsRead("未读");
            message.setSendTime(new Date());
            message.setCreateTime(new Date());
            messageMapper.insertMessage(message);
        }
    }

    /**
     * 修改考试信息
     *
     * @param exam 考试信息
     * @return 结果
     */
    @Override
    public void updateExam(Exam exam) {
        examMapper.updateExam(exam);
    }

    /**
     * 批量删除考试信息
     *
     * @param ids 需要删除的考试信息主键
     * @return 结果
     */
    @Override
    public void deleteExamByIds(Long[] ids) {
        examMapper.deleteExamByIds(ids);
    }

    /**
     * 删除考试信息信息
     *
     * @param id 考试信息主键
     * @return 结果
     */
    @Override
    public void deleteExamById(Long id) {
        examMapper.deleteExamById(id);
    }

}
