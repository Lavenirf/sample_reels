package com.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.entity.*;
import com.core.exception.ServiceException;
import com.core.mapper.ClazzMapper;
import com.core.mapper.ScoreMapper;
import com.core.mapper.StudentMapper;
import com.core.service.ClazzService;
import com.core.service.CourseService;
import com.core.service.ScoreService;
import com.core.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 成绩信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:32
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    private ScoreMapper scoreMapper;

    @Resource
    private ExamServiceImpl examService;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ClazzMapper clazzMapper;

    @Resource
    private CourseService courseService;

    @Resource
    private TeacherService teacherService;

    /**
     * 查询成绩信息
     *
     * @param id 成绩信息主键
     * @return 成绩信息
     */
    @Override
    public Score selectScoreById(Long id) {
        Score score = scoreMapper.selectScoreById(id);
        Exam exam = examService.selectExamById(score.getExamId());
        score.setExam(exam);
        //考试班级
        LambdaQueryWrapper<Clazz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Clazz::getId, Arrays.asList(exam.getClazzIds().split(",")));
        List<Clazz> clazzList = clazzMapper.selectList(lambdaQueryWrapper);
        String  clazzNames = clazzList.stream().map(Clazz::getName).collect(Collectors.joining(","));
        exam.setClazzNames(clazzNames);
        //课程
        Course course = courseService.selectCourseById(exam.getCourseId());
        exam.setCourse(course);
        return score;
    }

    /**
     * 分页查询成绩信息列表
     *
     * @param score 成绩信息
     * @return 成绩信息
     */
    @Override
    public IPage<Score> selectScorePage(Score score) {
        IPage<Score> iPage = new Page<>(score.getPage(), score.getPageSize());
        LambdaQueryWrapper<Score> lambdaQueryWrapper = new LambdaQueryWrapper<>(score);
        lambdaQueryWrapper.orderByDesc(Score::getCreateTime);
        IPage<Score> scoreIPage = scoreMapper.selectPage(iPage, lambdaQueryWrapper);
        for (Score scoreInfo : scoreIPage.getRecords()) {
            Exam exam = examService.selectExamById(scoreInfo.getExamId());
            scoreInfo.setExam(exam);
            //考试班级
            LambdaQueryWrapper<Clazz> clazzQueryWrapper = new LambdaQueryWrapper<>();
            clazzQueryWrapper.in(Clazz::getId,Arrays.asList(exam.getClazzIds().split(",")));
            List<Clazz> clazzeList = clazzMapper.selectList(clazzQueryWrapper);
            String  clazzNames = clazzeList.stream().map(Clazz::getName).collect(Collectors.joining(","));
            exam.setClazzNames(clazzNames);
            //课程
            Course course = courseService.selectCourseById(exam.getCourseId());
            exam.setCourse(course);
        }
        return scoreIPage;
    }

    /**
     * 查询成绩信息列表
     *
     * @param score 成绩信息
     * @return 成绩信息
     */
    @Override
    public List<Score> selectScoreList(Score score) {
        //学生查询自己有那些考试
        List<Score> scores = scoreMapper.selectScoreList(score);
        for (Score scoreDb : scores) {
            Exam exam = examService.selectExamById(scoreDb.getExamId());
            //考试班级
            LambdaQueryWrapper<Clazz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(Clazz::getId,Arrays.asList(exam.getClazzIds().split(",")));
            List<Clazz> clazzeList = clazzMapper.selectList(lambdaQueryWrapper);
            String  clazzNames = clazzeList.stream().map(Clazz::getName).collect(Collectors.joining(","));
            exam.setClazzNames(clazzNames);
            // 教师
            Teacher teacher = teacherService.selectTeacherById(exam.getTeacherId());
            exam.setTeacherName(teacher.getName());
            //课程
            Course course = courseService.selectCourseById(exam.getCourseId());
            exam.setCourse(course);

            scoreDb.setExam(exam);
        }
        return scores;
    }

    /**
     * 上传成绩信息
     *
     * @param scores 成绩信息
     * @return 结果
     */
    @Override
    public void updateScoreByStudentExamId(String examId, List<Score> scores) {
        for (Score score : scores) {
            // 获取学生主键，不存在则提示报错
            LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            studentLambdaQueryWrapper.eq(Student::getName, score.getStudentName())
                    .eq(Student::getNum, score.getStudentNum());
            Student student = studentMapper.selectOne(studentLambdaQueryWrapper);
            if (student == null) {
                throw new ServiceException(score.getStudentName() + "不存在或学号不对" );
            }
            // 保存学员成绩信息
            LambdaQueryWrapper<Score> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Score::getStudentId, student.getId())
                    .eq(Score::getExamId, examId);
            Score scoreDb = new Score();
            scoreDb.setScore(score.getScore());
            scoreDb.setStatus("已上传");
            scoreMapper.update(scoreDb, lambdaQueryWrapper);
        }
    }

    /**
     * 新增成绩信息
     *
     * @param score 成绩信息
     * @return 结果
     */
    @Override
    public void insertScore(Score score) {
        score.setCreateTime(new Date());
        scoreMapper.insertScore(score);
    }


    /**
     * 修改成绩信息
     *
     * @param score 成绩信息
     * @return 结果
     */
    @Override
    public void updateScore(Score score) {
        scoreMapper.updateScore(score);
    }

    /**
     * 批量删除成绩信息
     *
     * @param ids 需要删除的成绩信息主键
     * @return 结果
     */
    @Override
    public void deleteScoreByIds(Long[] ids) {
        scoreMapper.deleteScoreByIds(ids);
    }

    /**
     * 删除成绩信息信息
     *
     * @param id 成绩信息主键
     * @return 结果
     */
    @Override
    public void deleteScoreById(Long id) {
        scoreMapper.deleteScoreById(id);
    }

}
