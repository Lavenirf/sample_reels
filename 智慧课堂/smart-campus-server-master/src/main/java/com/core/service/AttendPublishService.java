package com.core.service;

import java.text.ParseException;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.AttendPublish;

/**
 * 教师发布考勤信息业务层接口
 *
 * @author zph
 * @date 2023-07-17 12:35:57
 */
public interface AttendPublishService {

    /**
     * 查询教师发布考勤信息
     *
     * @param id 教师发布考勤信息主键
     * @return 教师发布考勤信息
     */
     AttendPublish selectAttendPublishById(Long id);

    /**
     * 分页查询教师发布考勤信息列表
     *
     * @param attendPublish 教师发布考勤信息
     * @return 教师发布考勤信息集合
     */
    IPage<AttendPublish> selectAttendPublishPage(AttendPublish attendPublish);

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
    void insertAttendPublish(AttendPublish attendPublish) throws Exception;

    /**
     * 修改教师发布考勤信息
     *
     * @param attendPublish 教师发布考勤信息
     * @return 结果
     */
    void updateAttendPublish(AttendPublish attendPublish);

    /**
     * 批量删除教师发布考勤信息
     *
     * @param ids 需要删除的教师发布考勤信息主键集合
     * @return 结果
     */
    void deleteAttendPublishByIds(Long[] ids);

    /**
     * 删除教师发布考勤信息信息
     *
     * @param id 教师发布考勤信息主键
     * @return 结果
     */
    void deleteAttendPublishById(Long id);
}
