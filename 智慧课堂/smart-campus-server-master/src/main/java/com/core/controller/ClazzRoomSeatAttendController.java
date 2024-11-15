package com.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.core.entity.ClazzRoomSeat;
import com.core.entity.ClazzRoomSeatAttend;
import com.core.service.ClazzRoomSeatAttendService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 学员考勤座位信息控制层
 *
 * @author zjp
 * @date 2023-07-25 20:17:00
 */
@Api(tags = "学员考勤座位信息控制层")
@RestController
@RequestMapping("/api/room/seat/attend")
public class ClazzRoomSeatAttendController extends BaseController {

    @Resource
    private ClazzRoomSeatAttendService clazzRoomSeatAttendService;

    /**
     * 获取学员考勤座位信息详细信息
     */
    @ApiOperation(value = "获取学员考勤座位信息详细信息", notes = "获取学员考勤座位信息详细信息", response = ClazzRoomSeatAttend.class)
    @GetMapping(value = "/{id}")
    public ClazzRoomSeatAttend getInfo(@PathVariable("id") Long id) {
        return clazzRoomSeatAttendService.selectClazzRoomSeatAttendById(id);
    }

    /**
     * 分页查询学员考勤座位信息列表
     */
    @ApiOperation(value = "分页查询学员考勤座位信息列表", notes = "分页查询学员考勤座位信息列表", response = ClazzRoomSeatAttend.class)
    @GetMapping("/page")
    public IPage<ClazzRoomSeatAttend> page(ClazzRoomSeatAttend clazzRoomSeatAttend) {
        IPage<ClazzRoomSeatAttend> page = clazzRoomSeatAttendService.selectClazzRoomSeatAttendPage(clazzRoomSeatAttend);
        return page;
    }

    /**
     * 查询学员考勤座位信息列表
     */
    @ApiOperation(value = "查询学员考勤座位信息列表", notes = "查询学员考勤座位信息列表", response = ClazzRoomSeatAttend.class)
    @GetMapping("/list")
    public List<ClazzRoomSeatAttend> list(ClazzRoomSeatAttend clazzRoomSeatAttend) {
        List<ClazzRoomSeatAttend> list = clazzRoomSeatAttendService.selectClazzRoomSeatAttendList(clazzRoomSeatAttend);
        return list;
    }

    /**
     * 查询学员考勤座位信息列表Map
     */
    @ApiOperation(value = "查询学员考勤座位信息列表Map", notes = "查询学员考勤座位信息列表Map", response = ClazzRoomSeat.class)
    @GetMapping("/map")
    public Map<Long, ClazzRoomSeatAttend> map(ClazzRoomSeatAttend clazzRoomSeatAttend) {
        Map<Long, ClazzRoomSeatAttend> map = clazzRoomSeatAttendService.selectClazzRoomSeatAttendMap(clazzRoomSeatAttend);
        return map;
    }

    /**
     * 新增学员考勤座位信息
     */
    @ApiOperation(value = "新增学员考勤座位信息", notes = "新增学员考勤座位信息")
    @PostMapping
    public void add(@RequestBody ClazzRoomSeatAttend clazzRoomSeatAttend) throws JsonProcessingException {
        clazzRoomSeatAttendService.insertClazzRoomSeatAttend(clazzRoomSeatAttend);
    }

    /**
     * 修改学员考勤座位信息
     */
    @ApiOperation(value = "修改学员考勤座位信息", notes = "修改学员考勤座位信息")
    @PutMapping
    public void edit(@RequestBody ClazzRoomSeatAttend clazzRoomSeatAttend) {
        clazzRoomSeatAttendService.updateClazzRoomSeatAttend(clazzRoomSeatAttend);
    }

    /**
     * 删除学员考勤座位信息
     */
    @ApiOperation(value = "删除学员考勤座位信息", notes = "删除学员考勤座位信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        clazzRoomSeatAttendService.deleteClazzRoomSeatAttendByIds(ids);
    }

}
