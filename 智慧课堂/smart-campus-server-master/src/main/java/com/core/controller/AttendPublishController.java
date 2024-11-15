package com.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.core.entity.AttendPublish;
import com.core.service.AttendPublishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师发布考勤信息控制层
 *
 * @author zph
 * @date 2023-07-17 12:35:57
 */
@Api(tags = "教师发布考勤信息控制层")
@RestController
@RequestMapping("/api/attend/publish")
public class AttendPublishController extends BaseController {

    @Resource
    private AttendPublishService attendPublishService;

    /**
     * 获取教师发布考勤信息详细信息
     */
    @ApiOperation(value = "获取教师发布考勤信息详细信息", notes = "获取教师发布考勤信息详细信息", response = AttendPublish.class)
    @GetMapping(value = "/{id}")
    public AttendPublish getInfo(@PathVariable("id") Long id) {
        return attendPublishService.selectAttendPublishById(id);
    }

    /**
     * 分页查询教师发布考勤信息列表
     */
    @ApiOperation(value = "分页查询教师发布考勤信息列表", notes = "分页查询教师发布考勤信息列表", response = AttendPublish.class)
    @GetMapping("/page")
    public IPage<AttendPublish> page(AttendPublish attendPublish) {
        IPage<AttendPublish> page = attendPublishService.selectAttendPublishPage(attendPublish);
        return page;
    }

    /**
     * 查询教师发布考勤信息列表
     */
    @ApiOperation(value = "查询教师发布考勤信息列表", notes = "查询教师发布考勤信息列表", response = AttendPublish.class)
    @GetMapping("/list")
    public List<AttendPublish> list(AttendPublish attendPublish) {
        List<AttendPublish> list = attendPublishService.selectAttendPublishList(attendPublish);
        return list;
    }

    /**
     * 新增教师发布考勤信息
     */
    @ApiOperation(value = "新增教师发布考勤信息", notes = "新增教师发布考勤信息")
    @PostMapping
    public void add(@RequestBody AttendPublish attendPublish) throws Exception {
         attendPublishService.insertAttendPublish(attendPublish);
    }

    /**
     * 修改教师发布考勤信息
     */
    @ApiOperation(value = "修改教师发布考勤信息", notes = "修改教师发布考勤信息")
    @PutMapping
    public void edit(@RequestBody AttendPublish attendPublish) {
        attendPublishService.updateAttendPublish(attendPublish);
    }

    /**
     * 删除教师发布考勤信息
     */
    @ApiOperation(value = "删除教师发布考勤信息", notes = "删除教师发布考勤信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        attendPublishService.deleteAttendPublishByIds(ids);
    }

}
