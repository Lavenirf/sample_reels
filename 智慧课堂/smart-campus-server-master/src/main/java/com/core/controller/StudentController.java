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
import com.core.entity.Student;
import com.core.service.StudentService;

/**
 * 学员信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:33
 */
@Api(tags = "学员信息控制层")
@RestController
@RequestMapping("/api/student")
public class StudentController extends BaseController {

    @Resource
    private StudentService studentService;

    /**
     * 获取学员信息详细信息
     */
    @ApiOperation(value = "获取学员信息详细信息", notes = "获取学员信息详细信息", response = Student.class)
    @GetMapping(value = "/{id}")
    public Student getInfo(@PathVariable("id") Long id) {
        return studentService.selectStudentById(id);
    }

    /**
     * 分页查询学员信息列表
     */
    @ApiOperation(value = "分页查询学员信息列表", notes = "分页查询学员信息列表", response = Student.class)
    @GetMapping("/page")
    public IPage<Student> page(Student student) {
        IPage<Student> page = studentService.selectStudentPage(student);
        return page;
    }

    /**
     * 查询学员信息列表
     */
    @ApiOperation(value = "查询学员信息列表", notes = "查询学员信息列表", response = Student.class)
    @GetMapping("/list")
    public List<Student> list(Student student) {
        List<Student> list = studentService.selectStudentList(student);
        return list;
    }


    /**
     * 随机查询学员信息列表
     */
    @ApiOperation(value = "随机查询学员信息列表", notes = "随机查询学员信息列表", response = Student.class)
    @GetMapping("/list/random")
    public List<Student> randomList(Student student) {
        List<Student> list = studentService.selectRandomStudentList(student);
        return list;
    }


    /**
     * 新增学员信息
     */
    @ApiOperation(value = "新增学员信息", notes = "新增学员信息")
    @PostMapping
    public void add(@RequestBody Student student) {
         studentService.insertStudent(student);
    }

    /**
     * 修改学员信息
     */
    @ApiOperation(value = "修改学员信息", notes = "修改学员信息")
    @PutMapping
    public void edit(@RequestBody Student student) {
        studentService.updateStudent(student);
    }

    /**
     * 删除学员信息
     */
    @ApiOperation(value = "删除学员信息", notes = "删除学员信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        studentService.deleteStudentByIds(ids);
    }

}
