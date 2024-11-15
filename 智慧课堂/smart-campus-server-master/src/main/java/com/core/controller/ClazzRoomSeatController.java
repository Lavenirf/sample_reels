package com.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.core.entity.ClazzRoomSeat;
import com.core.service.ClazzRoomSeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 教室座位信息控制层
 *
 * @author zjp
 * @date 2023-07-25 20:16:59
 */
@Api(tags = "教室座位信息控制层")
@RestController
@RequestMapping("/api/room/seat")
public class ClazzRoomSeatController extends BaseController {

    @Resource
    private ClazzRoomSeatService clazzRoomSeatService;

    /**
     * 获取教室座位信息详细信息
     */
    @ApiOperation(value = "获取教室座位信息详细信息", notes = "获取教室座位信息详细信息", response = ClazzRoomSeat.class)
    @GetMapping(value = "/{id}")
    public ClazzRoomSeat getInfo(@PathVariable("id") Long id) {
        return clazzRoomSeatService.selectClazzRoomSeatById(id);
    }

    /**
     * 分页查询教室座位信息列表
     */
    @ApiOperation(value = "分页查询教室座位信息列表", notes = "分页查询教室座位信息列表", response = ClazzRoomSeat.class)
    @GetMapping("/page")
    public IPage<ClazzRoomSeat> page(ClazzRoomSeat clazzRoomSeat) {
        IPage<ClazzRoomSeat> page = clazzRoomSeatService.selectClazzRoomSeatPage(clazzRoomSeat);
        return page;
    }

    /**
     * 查询教室座位信息列表
     */
    @ApiOperation(value = "查询教室座位信息列表", notes = "查询教室座位信息列表", response = ClazzRoomSeat.class)
    @GetMapping("/list")
    public List<ClazzRoomSeat> list(ClazzRoomSeat clazzRoomSeat) {
        List<ClazzRoomSeat> list = clazzRoomSeatService.selectClazzRoomSeatList(clazzRoomSeat);
        return list;
    }

    /**
     * 查询教室座位信息Map
     */
    @ApiOperation(value = "查询教室座位信息Map", notes = "查询教室座位信息Map", response = ClazzRoomSeat.class)
    @GetMapping("/map")
    public Map<String, ClazzRoomSeat> map(ClazzRoomSeat clazzRoomSeat) {
        Map<String, ClazzRoomSeat> map = clazzRoomSeatService.selectClazzRoomSeatMap(clazzRoomSeat);
        return map;
    }

    /**
     * 新增教室座位信息
     */
    @ApiOperation(value = "新增教室座位信息", notes = "新增教室座位信息")
    @PostMapping
    public void add(@RequestBody ClazzRoomSeat clazzRoomSeat) {
         clazzRoomSeatService.insertClazzRoomSeat(clazzRoomSeat);
    }

    /**
     * 修改教室座位信息
     */
    @ApiOperation(value = "修改教室座位信息", notes = "修改教室座位信息")
    @PutMapping
    public void edit(@RequestBody ClazzRoomSeat clazzRoomSeat) {
        clazzRoomSeatService.updateClazzRoomSeat(clazzRoomSeat);
    }

    /**
     * 删除教室座位信息
     */
    @ApiOperation(value = "删除教室座位信息", notes = "删除教室座位信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        clazzRoomSeatService.deleteClazzRoomSeatByIds(ids);
    }

}
