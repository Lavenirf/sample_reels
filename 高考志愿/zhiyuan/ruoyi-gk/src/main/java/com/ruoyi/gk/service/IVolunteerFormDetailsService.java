package com.ruoyi.gk.service;

import com.ruoyi.gk.domain.VolunteerFormDetails;
import com.ruoyi.gk.domain.vo.VolunteerFormDetailsVo;

import java.util.List;

/**
 * 志愿详情Service接口
 * 
 * @author ruoyi
 * @date 2024-07-16
 */
public interface IVolunteerFormDetailsService 
{
    /**
     * 查询志愿详情
     * @return 志愿详情
     */
    List<VolunteerFormDetailsVo> selectVolunteerFormDetailsByVoId(Integer id,Integer paragraphId);

    /**
     * 查询志愿详情列表
     * 
     * @param volunteerFormDetails 志愿详情
     * @return 志愿详情集合
     */
    List<VolunteerFormDetails> selectVolunteerFormDetailsList(VolunteerFormDetails volunteerFormDetails);

    /**
     * 新增志愿详情
     * 
     * @param volunteerFormDetails 志愿详情
     * @return 结果
     */
    int insertVolunteerFormDetails(VolunteerFormDetails volunteerFormDetails);

    /**
     * 修改志愿详情
     * 
     * @param volunteerFormDetails 志愿详情
     * @return 结果
     */
    int updateVolunteerFormDetails(VolunteerFormDetails volunteerFormDetails,Integer oldOrder);
    /**
     * 删除志愿详情信息
     * 
     * @param id 志愿详情主键
     * @return 结果
     */
    int deleteVolunteerFormDetailsById(Long id);
}
