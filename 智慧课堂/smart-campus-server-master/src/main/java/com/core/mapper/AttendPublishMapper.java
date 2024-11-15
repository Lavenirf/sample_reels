package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.AttendPublish;

/**
 * 教师发布考勤信息数据库接口
 *
 * @author zph
 * @date 2023-07-17 12:35:57
 */
public interface AttendPublishMapper extends BaseMapper<AttendPublish> {

    /**
     * 查询教师发布考勤信息
     *
     * @param id 教师发布考勤信息主键
     * @return 教师发布考勤信息
     */
    AttendPublish selectAttendPublishById(Long id);

    /**
     * 查询教师发布考勤信息列表
     *
     * @param attendPublish 教师发布考勤信息
     * @return 教师发布考勤信息集合
     */
    List<AttendPublish> selectAttendPublishList(AttendPublish attendPublish);

    /**
     * 新增教师发布考勤信息
     *
     * @param attendPublish 教师发布考勤信息
     * @return 结果
     */
    void insertAttendPublish(AttendPublish attendPublish);

    /**
     * 修改教师发布考勤信息
     *
     * @param attendPublish 教师发布考勤信息
     * @return 结果
     */
    void updateAttendPublish(AttendPublish attendPublish);

    /**
     * 删除教师发布考勤信息
     *
     * @param id 教师发布考勤信息主键
     * @return 结果
     */
    void deleteAttendPublishById(Long id);

    /**
     * 批量删除教师发布考勤信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteAttendPublishByIds(Long[] ids);
}
