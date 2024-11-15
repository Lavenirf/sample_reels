package com.core.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.ClazzRoom;

/**
 * 教室信息业务层接口
 *
 * @author zph
 * @date 2023-07-14 19:35:28
 */
public interface ClazzRoomService {

    /**
     * 查询教室信息
     *
     * @param id 教室信息主键
     * @return 教室信息
     */
     ClazzRoom selectClazzRoomById(Long id);

    /**
     * 分页查询教室信息列表
     *
     * @param clazzRoom 教室信息
     * @return 教室信息集合
     */
    IPage<ClazzRoom> selectClazzRoomPage(ClazzRoom clazzRoom);

    /**
     * 查询教室信息列表
     *
     * @param clazzRoom 教室信息
     * @return 教室信息集合
     */
    List<ClazzRoom> selectClazzRoomList(ClazzRoom clazzRoom);

    /**
     * 新增教室信息
     *
     * @param clazzRoom 教室信息
     * @return 结果
     */
    void insertClazzRoom(ClazzRoom clazzRoom);

    /**
     * 修改教室信息
     *
     * @param clazzRoom 教室信息
     * @return 结果
     */
    void updateClazzRoom(ClazzRoom clazzRoom);

    /**
     * 批量删除教室信息
     *
     * @param ids 需要删除的教室信息主键集合
     * @return 结果
     */
    void deleteClazzRoomByIds(Long[] ids);

    /**
     * 删除教室信息信息
     *
     * @param id 教室信息主键
     * @return 结果
     */
    void deleteClazzRoomById(Long id);
}
