package com.core.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.ClazzRoomSeatAttend;

/**
 * 学员考勤座位信息业务层接口
 *
 * @author zjp
 * @date 2023-07-25 20:17:00
 */
public interface ClazzRoomSeatAttendService {

    /**
     * 查询学员考勤座位信息
     *
     * @param id 学员考勤座位信息主键
     * @return 学员考勤座位信息
     */
     ClazzRoomSeatAttend selectClazzRoomSeatAttendById(Long id);

    /**
     * 分页查询学员考勤座位信息列表
     *
     * @param clazzRoomSeatAttend 学员考勤座位信息
     * @return 学员考勤座位信息集合
     */
    IPage<ClazzRoomSeatAttend> selectClazzRoomSeatAttendPage(ClazzRoomSeatAttend clazzRoomSeatAttend);

    /**
     * 查询学员考勤座位信息列表
     *
     * @param clazzRoomSeatAttend 学员考勤座位信息
     * @return 学员考勤座位信息集合
     */
    List<ClazzRoomSeatAttend> selectClazzRoomSeatAttendList(ClazzRoomSeatAttend clazzRoomSeatAttend);

    /**
     * 查询学员考勤座位信息列表Map
     *
     * @param clazzRoomSeatAttend
     * @return
     */
    Map<Long, ClazzRoomSeatAttend> selectClazzRoomSeatAttendMap(ClazzRoomSeatAttend clazzRoomSeatAttend);

    /**
     * 新增学员考勤座位信息
     *
     * @param clazzRoomSeatAttend 学员考勤座位信息
     * @return 结果
     */
    void insertClazzRoomSeatAttend(ClazzRoomSeatAttend clazzRoomSeatAttend);

    /**
     * 修改学员考勤座位信息
     *
     * @param clazzRoomSeatAttend 学员考勤座位信息
     * @return 结果
     */
    void updateClazzRoomSeatAttend(ClazzRoomSeatAttend clazzRoomSeatAttend);

    /**
     * 批量删除学员考勤座位信息
     *
     * @param ids 需要删除的学员考勤座位信息主键集合
     * @return 结果
     */
    void deleteClazzRoomSeatAttendByIds(Long[] ids);

    /**
     * 删除学员考勤座位信息信息
     *
     * @param id 学员考勤座位信息主键
     * @return 结果
     */
    void deleteClazzRoomSeatAttendById(Long id);

}
