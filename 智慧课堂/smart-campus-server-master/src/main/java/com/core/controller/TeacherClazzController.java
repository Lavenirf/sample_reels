package com.core.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;

import com.core.entity.Clazz;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.core.entity.TeacherClazz;
import com.core.service.TeacherClazzService;

/**
 * 教师班级信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:39
 */
@Api(tags = "教师班级信息控制层")
@RestController
@RequestMapping("/api/teacher/clazz")
public class TeacherClazzController extends BaseController {

    @Resource
    private TeacherClazzService teacherClazzService;

    /**
     * 通过教师主键获取班级列表
     */
    @GetMapping(value = "/teacherId")
    public List<Clazz> getClazzByTeacherId(@RequestParam Long teacherId) {
        return teacherClazzService.selectClazzByTeacherId(teacherId);
    }

    /**
     * 获取教师班级信息详细信息
     */
    @ApiOperation(value = "获取教师班级信息详细信息", notes = "获取教师班级信息详细信息", response = TeacherClazz.class)
    @GetMapping(value = "/{id}")
    public TeacherClazz getInfo(@PathVariable("id") Long id) {
        return teacherClazzService.selectTeacherClazzById(id);
    }

    /**
     * 分页查询教师班级信息列表
     */
    @ApiOperation(value = "分页查询教师班级信息列表", notes = "分页查询教师班级信息列表", response = TeacherClazz.class)
    @GetMapping("/page")
    public IPage<TeacherClazz> page(TeacherClazz teacherClazz) {
        IPage<TeacherClazz> page = teacherClazzService.selectTeacherClazzPage(teacherClazz);
        return page;
    }

    /**
     * 查询教师班级信息列表
     */
    @ApiOperation(value = "查询教师班级信息列表", notes = "查询教师班级信息列表", response = TeacherClazz.class)
    @GetMapping("/list")
    public List<TeacherClazz> list(TeacherClazz teacherClazz) {
        List<TeacherClazz> list = teacherClazzService.selectTeacherClazzList(teacherClazz);
        return list;
    }

    /**
     * 新增教师班级信息
     */
    @ApiOperation(value = "新增教师班级信息", notes = "新增教师班级信息")
    @PostMapping
    public void add(@RequestBody TeacherClazz teacherClazz) {
         teacherClazzService.insertTeacherClazz(teacherClazz);
    }

    /**
     * 修改教师班级信息
     */
    @ApiOperation(value = "修改教师班级信息", notes = "修改教师班级信息")
    @PutMapping
    public void edit(@RequestBody TeacherClazz teacherClazz) {
        teacherClazzService.updateTeacherClazz(teacherClazz);
    }

    /**
     * 删除教师班级信息
     */
    @ApiOperation(value = "删除教师班级信息", notes = "删除教师班级信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        teacherClazzService.deleteTeacherClazzByIds(ids);
    }

}
