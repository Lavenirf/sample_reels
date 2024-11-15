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
import com.core.entity.CourseTable;
import com.core.service.CourseTableService;

/**
 * 课程表信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
@Api(tags = "课程表信息控制层")
@RestController
@RequestMapping("/api/course/table")
public class CourseTableController extends BaseController {

    @Resource
    private CourseTableService courseTableService;

    /**
     * 获取课程表信息详细信息
     */
    @ApiOperation(value = "获取课程表信息详细信息", notes = "获取课程表信息详细信息", response = CourseTable.class)
    @GetMapping(value = "/{id}")
    public CourseTable getInfo(@PathVariable("id") Long id) {
        return courseTableService.selectCourseTableById(id);
    }

    /**
     * 分页查询课程表信息列表
     */
    @ApiOperation(value = "分页查询课程表信息列表", notes = "分页查询课程表信息列表", response = CourseTable.class)
    @GetMapping("/page")
    public IPage<CourseTable> page(CourseTable courseTable) {
        IPage<CourseTable> page = courseTableService.selectCourseTablePage(courseTable);
        return page;
    }

    /**
     * 查询课程表信息列表
     */
    @ApiOperation(value = "查询课程表信息列表", notes = "查询课程表信息列表", response = CourseTable.class)
    @GetMapping("/list")
    public List<CourseTable> list(CourseTable courseTable) {
        List<CourseTable> list = courseTableService.selectCourseTableList(courseTable);
        return list;
    }

    /**
     * 用户查询课程表信息
     */
    @ApiOperation(value = "用户查询课程表信息", notes = "用户查询课程表信息", response = CourseTable.class)
    @GetMapping("/list/user")
    public List<CourseTable> listByUser(CourseTable courseTable) {
        List<CourseTable> list = courseTableService.selectCourseTableListByUser(courseTable);
        return list;
    }

    /**
     * 用户查询今日课程信息
     */
    @ApiOperation(value = "用户查询今日课程信息", notes = "用户查询今日课程信息", response = CourseTable.class)
    @GetMapping("/list/user/today")
    public List<CourseTable> todayListByUser(CourseTable courseTable) {
        List<CourseTable> list = courseTableService.selectTodayCourseTableListByUser(courseTable);
        return list;
    }


    /**
     * 新增课程表信息
     */
    @ApiOperation(value = "新增课程表信息", notes = "新增课程表信息")
    @PostMapping
    public void add(@RequestBody CourseTable courseTable) {
         courseTableService.insertCourseTable(courseTable);
    }

    /**
     * 修改课程表信息
     */
    @ApiOperation(value = "修改课程表信息", notes = "修改课程表信息")
    @PutMapping
    public void edit(@RequestBody CourseTable courseTable) {
        courseTableService.updateCourseTable(courseTable);
    }

    /**
     * 删除课程表信息
     */
    @ApiOperation(value = "删除课程表信息", notes = "删除课程表信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        courseTableService.deleteCourseTableByIds(ids);
    }

}
