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
import com.core.entity.ClazzRoom;
import com.core.service.ClazzRoomService;

/**
 * 教室信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:28
 */
@Api(tags = "教室信息控制层")
@RestController
@RequestMapping("/api/clazz/room")
public class ClazzRoomController extends BaseController {

    @Resource
    private ClazzRoomService clazzRoomService;

    /**
     * 获取教室信息详细信息
     */
    @ApiOperation(value = "获取教室信息详细信息", notes = "获取教室信息详细信息", response = ClazzRoom.class)
    @GetMapping(value = "/{id}")
    public ClazzRoom getInfo(@PathVariable("id") Long id) {
        return clazzRoomService.selectClazzRoomById(id);
    }

    /**
     * 分页查询教室信息列表
     */
    @ApiOperation(value = "分页查询教室信息列表", notes = "分页查询教室信息列表", response = ClazzRoom.class)
    @GetMapping("/page")
    public IPage<ClazzRoom> page(ClazzRoom clazzRoom) {
        IPage<ClazzRoom> page = clazzRoomService.selectClazzRoomPage(clazzRoom);
        return page;
    }

    /**
     * 查询教室信息列表
     */
    @ApiOperation(value = "查询教室信息列表", notes = "查询教室信息列表", response = ClazzRoom.class)
    @GetMapping("/list")
    public List<ClazzRoom> list(ClazzRoom clazzRoom) {
        List<ClazzRoom> list = clazzRoomService.selectClazzRoomList(clazzRoom);
        return list;
    }

    /**
     * 新增教室信息
     */
    @ApiOperation(value = "新增教室信息", notes = "新增教室信息")
    @PostMapping
    public void add(@RequestBody ClazzRoom clazzRoom) {
         clazzRoomService.insertClazzRoom(clazzRoom);
    }

    /**
     * 修改教室信息
     */
    @ApiOperation(value = "修改教室信息", notes = "修改教室信息")
    @PutMapping
    public void edit(@RequestBody ClazzRoom clazzRoom) {
        clazzRoomService.updateClazzRoom(clazzRoom);
    }

    /**
     * 删除教室信息
     */
    @ApiOperation(value = "删除教室信息", notes = "删除教室信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        clazzRoomService.deleteClazzRoomByIds(ids);
    }

}
