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
import com.core.entity.Clazz;
import com.core.service.ClazzService;

/**
 * 班级信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:37
 */
@Api(tags = "班级信息控制层")
@RestController
@RequestMapping("/api/clazz")
public class ClazzController extends BaseController {

    @Resource
    private ClazzService clazzService;

    /**
     * 获取班级信息详细信息
     */
    @ApiOperation(value = "获取班级信息详细信息", notes = "获取班级信息详细信息", response = Clazz.class)
    @GetMapping(value = "/{id}")
    public Clazz getInfo(@PathVariable("id") Long id) {
        return clazzService.selectClazzById(id);
    }

    /**
     * 分页查询班级信息列表
     */
    @ApiOperation(value = "分页查询班级信息列表", notes = "分页查询班级信息列表", response = Clazz.class)
    @GetMapping("/page")
    public IPage<Clazz> page(Clazz clazz) {
        IPage<Clazz> page = clazzService.selectClazzPage(clazz);
        return page;
    }

    /**
     * 查询班级信息列表
     */
    @ApiOperation(value = "查询班级信息列表", notes = "查询班级信息列表", response = Clazz.class)
    @GetMapping("/list")
    public List<Clazz> list(Clazz clazz) {
        List<Clazz> list = clazzService.selectClazzList(clazz);
        return list;
    }

    /**
     * 新增班级信息
     */
    @ApiOperation(value = "新增班级信息", notes = "新增班级信息")
    @PostMapping
    public void add(@RequestBody Clazz clazz) {
         clazzService.insertClazz(clazz);
    }

    /**
     * 修改班级信息
     */
    @ApiOperation(value = "修改班级信息", notes = "修改班级信息")
    @PutMapping
    public void edit(@RequestBody Clazz clazz) {
        clazzService.updateClazz(clazz);
    }

    /**
     * 删除班级信息
     */
    @ApiOperation(value = "删除班级信息", notes = "删除班级信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        clazzService.deleteClazzByIds(ids);
    }

}
