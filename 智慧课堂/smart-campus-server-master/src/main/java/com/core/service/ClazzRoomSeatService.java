package com.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.core.entity.ClazzRoomSeat;

import java.util.List;
import java.util.Map;

/**
 * 教室座位信息业务层接口
 *
 * @author zjp
 * @date 2023-07-25 20:16:59
 */
public interface ClazzRoomSeatService {

    /**
     * 查询教室座位信息
     *
     * @param id 教室座位信息主键
     * @return 教室座位信息
     */
     ClazzRoomSeat selectClazzRoomSeatById(Long id);

    /**
     * 分页查询教室座位信息列表
     *
     * @param clazzRoomSeat 教室座位信息
     * @return 教室座位信息集合
     */
    IPage<ClazzRoomSeat> selectClazzRoomSeatPage(ClazzRoomSeat clazzRoomSeat);

    /**
     * 查询教室座位信息列表
     *
     * @param clazzRoomSeat 教室座位信息
     * @return 教室座位信息集合
     */
    List<ClazzRoomSeat> selectClazzRoomSeatList(ClazzRoomSeat clazzRoomSeat);

    /**
     *  查询教室座位信息Map
     *
     * @param clazzRoomSeat
     * @return
     */
    Map<String, ClazzRoomSeat> selectClazzRoomSeatMap(ClazzRoomSeat clazzRoomSeat);

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
     * 批量删除教室座位信息
     *
     * @param ids 需要删除的教室座位信息主键集合
     * @return 结果
     */
    void deleteClazzRoomSeatByIds(Long[] ids);

    /**
     * 删除教室座位信息信息
     *
     * @param id 教室座位信息主键
     * @return 结果
     */
    void deleteClazzRoomSeatById(Long id);
}
