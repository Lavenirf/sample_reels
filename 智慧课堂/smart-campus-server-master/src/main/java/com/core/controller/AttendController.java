package com.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.core.entity.Attend;
import com.core.service.AttendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考勤信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:37
 */
@Api(tags = "考勤信息控制层")
@RestController
@RequestMapping("/api/attend")
public class AttendController extends BaseController {

    @Resource
    private AttendService attendService;

    /**
     * 考勤学生签到
     */
    @GetMapping("/sign")
    public void sign(@RequestParam Long studentId, @RequestParam Double longitude, @RequestParam Double latitude
            , @RequestParam Long attendId) {
        attendService.sign(studentId, longitude, latitude, attendId);
    }


    /**
     * 获取考勤信息详细信息
     */
    @ApiOperation(value = "获取考勤信息详细信息", notes = "获取考勤信息详细信息", response = Attend.class)
    @GetMapping(value = "/{id}")
    public Attend getInfo(@PathVariable("id") Long id) {
        return attendService.selectAttendById(id);
    }

    /**
     * 分页查询考勤信息列表
     */
    @ApiOperation(value = "分页查询考勤信息列表", notes = "分页查询考勤信息列表", response = Attend.class)
    @GetMapping("/page")
    public IPage<Attend> page(Attend attend) {
        IPage<Attend> page = attendService.selectAttendPage(attend);
        return page;
    }

    /**
     * 查询考勤信息列表
     */
    @ApiOperation(value = "查询考勤信息列表", notes = "查询考勤信息列表", response = Attend.class)
    @GetMapping("/list")
    public List<Attend> list(Attend attend) {
        List<Attend> list = attendService.selectAttendList(attend);
        return list;
    }

    /**
     * 新增考勤信息
     */
    @ApiOperation(value = "新增考勤信息", notes = "新增考勤信息")
    @PostMapping
    public void add(@RequestBody Attend attend) {
        attendService.insertAttend(attend);
    }

    /**
     * 修改考勤信息
     */
    @ApiOperation(value = "修改考勤信息", notes = "修改考勤信息")
    @PutMapping
    public void edit(@RequestBody Attend attend) {
        attendService.updateAttend(attend);
    }

    /**
     * 删除考勤信息
     */
    @ApiOperation(value = "删除考勤信息", notes = "删除考勤信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        attendService.deleteAttendByIds(ids);
    }

}
