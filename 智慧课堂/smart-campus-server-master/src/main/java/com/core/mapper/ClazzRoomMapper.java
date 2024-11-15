package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.ClazzRoom;

/**
 * 教室信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:28
 */
public interface ClazzRoomMapper extends BaseMapper<ClazzRoom> {

    /**
     * 查询教室信息
     *
     * @param id 教室信息主键
     * @return 教室信息
     */
    ClazzRoom selectClazzRoomById(Long id);

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
     * 删除教室信息
     *
     * @param id 教室信息主键
     * @return 结果
     */
    void deleteClazzRoomById(Long id);

    /**
     * 批量删除教室信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteClazzRoomByIds(Long[] ids);
}
