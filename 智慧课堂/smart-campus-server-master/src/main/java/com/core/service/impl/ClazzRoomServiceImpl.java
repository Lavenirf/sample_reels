package com.core.service.impl;

import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import java.util.List;

import com.core.entity.ClazzRoomSeat;
import org.springframework.stereotype.Service;
import com.core.mapper.ClazzRoomMapper;
import com.core.entity.ClazzRoom;
import com.core.service.ClazzRoomService;

/**
 * 教室信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:28
 */
@Service
public class ClazzRoomServiceImpl implements ClazzRoomService {

    @Resource
    private ClazzRoomMapper clazzRoomMapper;

    /**
     * 查询教室信息
     *
     * @param id 教室信息主键
     * @return 教室信息
     */
    @Override
    public ClazzRoom selectClazzRoomById(Long id) {
        return clazzRoomMapper.selectClazzRoomById(id);
    }

    /**
     * 分页查询教室信息列表
     *
     * @param clazzRoom 教室信息
     * @return 教室信息
     */
    @Override
    public IPage<ClazzRoom> selectClazzRoomPage(ClazzRoom clazzRoom) {
        IPage<ClazzRoom> iPage = new Page<>(clazzRoom.getPage(), clazzRoom.getPageSize());
        LambdaQueryWrapper<ClazzRoom> lambdaQueryWrapper = new LambdaQueryWrapper<>(clazzRoom);
        lambdaQueryWrapper.orderByDesc(ClazzRoom::getCreateTime);
        return clazzRoomMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询教室信息列表
     *
     * @param clazzRoom 教室信息
     * @return 教室信息
     */
    @Override
    public List<ClazzRoom> selectClazzRoomList(ClazzRoom clazzRoom) {
        return clazzRoomMapper.selectClazzRoomList(clazzRoom);
    }

    /**
     * 新增教室信息
     *
     * @param clazzRoom 教室信息
     * @return 结果
     */
    @Override
    public void insertClazzRoom(ClazzRoom clazzRoom) {
        clazzRoom.setCreateTime(new Date());
        clazzRoomMapper.insertClazzRoom(clazzRoom);
    }

    /**
     * 修改教室信息
     *
     * @param clazzRoom 教室信息
     * @return 结果
     */
    @Override
    public void updateClazzRoom(ClazzRoom clazzRoom) {
        clazzRoomMapper.updateClazzRoom(clazzRoom);
    }

    /**
     * 批量删除教室信息
     *
     * @param ids 需要删除的教室信息主键
     * @return 结果
     */
    @Override
    public void deleteClazzRoomByIds(Long[] ids) {
        clazzRoomMapper.deleteClazzRoomByIds(ids);
    }

    /**
     * 删除教室信息信息
     *
     * @param id 教室信息主键
     * @return 结果
     */
    @Override
    public void deleteClazzRoomById(Long id) {
        clazzRoomMapper.deleteClazzRoomById(id);
    }

}
