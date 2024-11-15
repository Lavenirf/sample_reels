package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.Attend;

/**
 * 考勤信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:37
 */
public interface AttendMapper extends BaseMapper<Attend> {

    /**
     * 查询考勤信息
     *
     * @param id 考勤信息主键
     * @return 考勤信息
     */
    Attend selectAttendById(Long id);

    /**
     * 查询考勤信息列表
     *
     * @param attend 考勤信息
     * @return 考勤信息集合
     */
    List<Attend> selectAttendList(Attend attend);

    /**
     * 新增考勤信息
     *
     * @param attend 考勤信息
     * @return 结果
     */
    void insertAttend(Attend attend);

    /**
     * 修改考勤信息
     *
     * @param attend 考勤信息
     * @return 结果
     */
    void updateAttend(Attend attend);

    /**
     * 删除考勤信息
     *
     * @param id 考勤信息主键
     * @return 结果
     */
    void deleteAttendById(Long id);

    /**
     * 批量删除考勤信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteAttendByIds(Long[] ids);
}
