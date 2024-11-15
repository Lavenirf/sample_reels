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
import com.core.entity.Homework;
import com.core.service.HomeworkService;

/**
 * 作业信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
@Api(tags = "作业信息控制层")
@RestController
@RequestMapping("/api/homework")
public class HomeworkController extends BaseController {

    @Resource
    private HomeworkService homeworkService;

    /**
     * 获取作业信息详细信息
     */
    @ApiOperation(value = "获取作业信息详细信息", notes = "获取作业信息详细信息", response = Homework.class)
    @GetMapping(value = "/{id}")
    public Homework getInfo(@PathVariable("id") Long id) {
        return homeworkService.selectHomeworkById(id);
    }

    /**
     * 分页查询作业信息列表
     */
    @ApiOperation(value = "分页查询作业信息列表", notes = "分页查询作业信息列表", response = Homework.class)
    @GetMapping("/page")
    public IPage<Homework> page(Homework homework) {
        IPage<Homework> page = homeworkService.selectHomeworkPage(homework);
        return page;
    }

    /**
     * 查询作业信息列表
     */
    @ApiOperation(value = "查询作业信息列表", notes = "查询作业信息列表", response = Homework.class)
    @GetMapping("/list")
    public List<Homework> list(Homework homework) {
        List<Homework> list = homeworkService.selectHomeworkList(homework);
        return list;
    }

    /**
     * 新增作业信息
     */
    @ApiOperation(value = "新增作业信息", notes = "新增作业信息")
    @PostMapping
    public void add(@RequestBody Homework homework) {
         homeworkService.insertHomework(homework);
    }

    /**
     * 修改作业信息
     */
    @ApiOperation(value = "修改作业信息", notes = "修改作业信息")
    @PutMapping
    public void edit(@RequestBody Homework homework) {
        homeworkService.updateHomework(homework);
    }

    /**
     * 删除作业信息
     */
    @ApiOperation(value = "删除作业信息", notes = "删除作业信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        homeworkService.deleteHomeworkByIds(ids);
    }

}
