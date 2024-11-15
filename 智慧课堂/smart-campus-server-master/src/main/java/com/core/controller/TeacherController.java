package com.core.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;

import com.core.entity.Course;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.core.entity.Teacher;
import com.core.service.TeacherService;

/**
 * 教师信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:39
 */
@Api(tags = "教师信息控制层")
@RestController
@RequestMapping("/api/teacher")
public class TeacherController extends BaseController {

    @Resource
    private TeacherService teacherService;

    /**
     * 获取教师信息详细信息
     */
    @ApiOperation(value = "获取教师信息详细信息", notes = "获取教师信息详细信息", response = Teacher.class)
    @GetMapping(value = "/{id}")
    public Teacher getInfo(@PathVariable("id") Long id) {
        return teacherService.selectTeacherById(id);
    }

    /**
     * 分页查询教师信息列表
     */
    @ApiOperation(value = "分页查询教师信息列表", notes = "分页查询教师信息列表", response = Teacher.class)
    @GetMapping("/page")
    public IPage<Teacher> page(Teacher teacher) {
        IPage<Teacher> page = teacherService.selectTeacherPage(teacher);
        return page;
    }

    /**
     * 查询教师信息列表
     */
    @ApiOperation(value = "查询教师信息列表", notes = "查询教师信息列表", response = Teacher.class)
    @GetMapping("/list")
    public List<Teacher> list(Teacher teacher) {
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        return list;
    }

    /**
     * 查询教师课程列表
     */
    @ApiOperation(value = "查询教师课程列表", notes = "查询教师课程列表", response = Course.class)
    @GetMapping("/course")
    public List<Course> list(@RequestParam Long teacherId) {
        List<Course> list = teacherService.selectTeacherCourseList(teacherId);
        return list;
    }

    /**
     * 新增教师信息
     */
    @ApiOperation(value = "新增教师信息", notes = "新增教师信息")
    @PostMapping
    public void add(@RequestBody Teacher teacher) {
         teacherService.insertTeacher(teacher);
    }

    /**
     * 修改教师信息
     */
    @ApiOperation(value = "修改教师信息", notes = "修改教师信息")
    @PutMapping
    public void edit(@RequestBody Teacher teacher) {
        teacherService.updateTeacher(teacher);
    }

    /**
     * 删除教师信息
     */
    @ApiOperation(value = "删除教师信息", notes = "删除教师信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        teacherService.deleteTeacherByIds(ids);
    }

}
