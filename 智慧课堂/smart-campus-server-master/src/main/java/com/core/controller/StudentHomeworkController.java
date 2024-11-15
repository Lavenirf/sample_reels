package com.core.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.core.entity.StudentHomework;
import com.core.service.StudentHomeworkService;

/**
 * 学生作业信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:33
 */
@Api(tags = "学生作业信息控制层")
@RestController
@RequestMapping("/api/student/homework")
public class StudentHomeworkController extends BaseController {

    @Resource
    private StudentHomeworkService studentHomeworkService;

    /**
     * 获取学生作业信息详细信息
     */
    @ApiOperation(value = "获取学生作业信息详细信息", notes = "获取学生作业信息详细信息", response = StudentHomework.class)
    @GetMapping(value = "/{id}")
    public StudentHomework getInfo(@PathVariable("id") Long id) {
        return studentHomeworkService.selectStudentHomeworkById(id);
    }

    /**
     * 分页查询学生作业信息列表
     */
    @ApiOperation(value = "分页查询学生作业信息列表", notes = "分页查询学生作业信息列表", response = StudentHomework.class)
    @GetMapping("/page")
    public IPage<StudentHomework> page(StudentHomework studentHomework) {
        IPage<StudentHomework> page = studentHomeworkService.selectStudentHomeworkPage(studentHomework);
        return page;
    }

    /**
     * 查询学生
     */
    @PostMapping("/totalStudent")
    public Long totalStudent(Long id) {
        return studentHomeworkService.totalStudent(id);
    }

    /**
     * 查询作业完成情况，返回学生完成数量
     */
    @PostMapping("/count")
    public Long studentCountList(Long id) {
        return studentHomeworkService.studentCount(id);
    }

    /**
     * 查询学生作业信息列表
     */
    @ApiOperation(value = "查询学生作业信息列表", notes = "查询学生作业信息列表", response = StudentHomework.class)
    @GetMapping("/list")
    public List<StudentHomework> list(StudentHomework studentHomework) {
        List<StudentHomework> list = studentHomeworkService.selectStudentHomeworkList(studentHomework);
        return list;
    }


    /**
     * 新增学生作业信息
     */
    @ApiOperation(value = "新增学生作业信息", notes = "新增学生作业信息")
    @PostMapping
    public void add(@RequestBody StudentHomework studentHomework) {
         studentHomeworkService.insertStudentHomework(studentHomework);
    }

    /**
     * 批改学生作业信息
     */
    @ApiOperation(value = "批改学生作业信息", notes = "批改学生作业信息")
    @PutMapping("/edit")
    public void edit(@RequestBody StudentHomework studentHomework) {
        studentHomeworkService.editStudentHomework(studentHomework);
    }

    /**
     * 学生上传作业信息
     */
    @ApiOperation(value = "学生上传作业信息", notes = "学生上传作业信息")
    @PutMapping("/update")
    public void update(@RequestBody StudentHomework studentHomework) {
        studentHomeworkService.updateStudentHomework(studentHomework);
    }

    /**
     * 删除学生作业信息
     */
    @ApiOperation(value = "删除学生作业信息", notes = "删除学生作业信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        studentHomeworkService.deleteStudentHomeworkByIds(ids);
    }

}
