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
import com.core.entity.Course;
import com.core.service.CourseService;

/**
 * 课程信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:29
 */
@Api(tags = "课程信息控制层")
@RestController
@RequestMapping("/api/course")
public class CourseController extends BaseController {

    @Resource
    private CourseService courseService;

    /**
     * 获取课程信息详细信息
     */
    @ApiOperation(value = "获取课程信息详细信息", notes = "获取课程信息详细信息", response = Course.class)
    @GetMapping(value = "/{id}")
    public Course getInfo(@PathVariable("id") Long id) {
        return courseService.selectCourseById(id);
    }

    /**
     * 分页查询课程信息列表
     */
    @ApiOperation(value = "分页查询课程信息列表", notes = "分页查询课程信息列表", response = Course.class)
    @GetMapping("/page")
    public IPage<Course> page(Course course) {
        IPage<Course> page = courseService.selectCoursePage(course);
        return page;
    }

    /**
     * 查询课程信息列表
     */
    @ApiOperation(value = "查询课程信息列表", notes = "查询课程信息列表", response = Course.class)
    @GetMapping("/list")
    public List<Course> list(Course course) {
        List<Course> list = courseService.selectCourseList(course);
        return list;
    }

    /**
     * 新增课程信息
     */
    @ApiOperation(value = "新增课程信息", notes = "新增课程信息")
    @PostMapping
    public void add(@RequestBody Course course) {
         courseService.insertCourse(course);
    }

    /**
     * 修改课程信息
     */
    @ApiOperation(value = "修改课程信息", notes = "修改课程信息")
    @PutMapping
    public void edit(@RequestBody Course course) {
        courseService.updateCourse(course);
    }

    /**
     * 删除课程信息
     */
    @ApiOperation(value = "删除课程信息", notes = "删除课程信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        courseService.deleteCourseByIds(ids);
    }

}
