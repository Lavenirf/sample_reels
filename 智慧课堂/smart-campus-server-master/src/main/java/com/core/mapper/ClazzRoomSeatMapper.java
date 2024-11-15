package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.ClazzRoomSeat;

/**
 * 教室座位信息数据库接口
 *
 * @author zjp
 * @date 2023-07-25 20:16:59
 */
public interface ClazzRoomSeatMapper extends BaseMapper<ClazzRoomSeat> {

    /**
     * 查询教室座位信息
     *
     * @param id 教室座位信息主键
     * @return 教室座位信息
     */
    ClazzRoomSeat selectClazzRoomSeatById(Long id);

    /**
     * 查询教室座位信息列表
     *
     * @param clazzRoomSeat 教室座位信息
     * @return 教室座位信息集合
     */
    List<ClazzRoomSeat> selectClazzRoomSeatList(ClazzRoomSeat clazzRoomSeat);

    /**
     * 新增教室座位信息
     *
     * @param clazzRoomSeat 教室座位信息
     * @return 结果
     */
    void insertClazzRoomSeat(ClazzRoomSeat clazzRoomSeat);

    /**
     * 修改教室座位信息
     *
     * @param clazzRoomSeat 教室座位信息
     * @return 结果
     */
    void updateClazzRoomSeat(ClazzRoomSeat clazzRoomSeat);

    /**
     * 删除教室座位信息
     *
     * @param id 教室座位信息主键
     * @return 结果
     */
    void deleteClazzRoomSeatById(Long id);

    /**
     * 批量删除教室座位信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteClazzRoomSeatByIds(Long[] ids);
}
