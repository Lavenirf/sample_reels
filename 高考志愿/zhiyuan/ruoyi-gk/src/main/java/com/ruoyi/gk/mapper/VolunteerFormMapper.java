package com.ruoyi.gk.mapper;

import com.ruoyi.gk.domain.VolunteerForm;

import java.util.List;


/**
 * 志愿Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-16
 */
public interface VolunteerFormMapper 
{
    /**
     * 查询志愿
     *
     * @return 志愿
     */
    List<VolunteerForm> selectVolunteerFormByUserId(Long id);

    /**
     * 查询志愿
     *
     * @return 志愿
     */
    VolunteerForm selectVolunteerFormById(Long id);

    /**
     * 查询志愿列表
     * 
     * @param volunteerForm 志愿
     * @return 志愿集合
     */
    List<VolunteerForm> selectVolunteerFormList(VolunteerForm volunteerForm);

    /**
     * 新增志愿
     * 
     * @param volunteerForm 志愿
     * @return 结果
     */
    Long insertVolunteerForm(VolunteerForm volunteerForm);

    /**
     * 修改志愿
     * 
     * @param volunteerForm 志愿
     * @return 结果
     */
    int updateVolunteerForm(VolunteerForm volunteerForm);

    /**
     * 删除志愿
     * 
     * @param id 志愿主键
     * @return 结果
     */
    int deleteVolunteerFormById(Long id);

}
