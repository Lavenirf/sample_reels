package com.core.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.core.entity.Exam;
import com.core.service.ExamService;

/**
 * 考试信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
@Api(tags = "考试信息控制层")
@RestController
@RequestMapping("/api/exam")
public class ExamController extends BaseController {

    @Resource
    private ExamService examService;

    /**
     * 获取考试信息详细信息
     */
    @ApiOperation(value = "获取考试信息详细信息", notes = "获取考试信息详细信息", response = Exam.class)
    @GetMapping(value = "/{id}")
    public Exam getInfo(@PathVariable("id") Long id) {
        return examService.selectExamById(id);
    }

    /**
     * 分页查询考试信息列表
     */
    @ApiOperation(value = "分页查询考试信息列表", notes = "分页查询考试信息列表", response = Exam.class)
    @GetMapping("/page")
    public IPage<Exam> page(Exam exam) {
        IPage<Exam> page = examService.selectExamPage(exam);
        return page;
    }

    /**
     * 通过学生id查询当前学生考试信息列表
     */
    @GetMapping("/student")
    public List<Exam> studentExamList(@RequestParam Long studentId) {
        List<Exam> exams = examService.studentExamList(studentId);
        return exams;
    }

    /**
     * 查询考试信息列表
     */
    @ApiOperation(value = "查询考试信息列表", notes = "查询考试信息列表", response = Exam.class)
    @GetMapping("/list")
    public List<Exam> list(Exam exam) {
        List<Exam> list = examService.selectExamList(exam);
        return list;
    }

    /**
     * 新增考试信息
     */
    @ApiOperation(value = "新增考试信息", notes = "新增考试信息")
    @PostMapping
    public void add(@RequestBody Exam exam) {
         examService.insertExam(exam);
    }

    /**
     * 修改考试信息
     */
    @ApiOperation(value = "修改考试信息", notes = "修改考试信息")
    @PutMapping
    public void edit(@RequestBody Exam exam) {
        examService.updateExam(exam);
    }

    /**
     * 删除考试信息
     */
    @ApiOperation(value = "删除考试信息", notes = "删除考试信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        examService.deleteExamByIds(ids);
    }

}
