package com.ruoyi.gk.mapper;

import com.ruoyi.gk.domain.VolunteerFormDetails;
import com.ruoyi.gk.domain.vo.VolunteerFormDetailsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 志愿详情Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-16
 */
public interface VolunteerFormDetailsMapper 
{
    /**
     * 新增修改使用
     */
    List<VolunteerFormDetails> selectVolunteerFormDetailsByVoId(@Param("voId") Integer voId,@Param("paragraphId") Integer paragraphId);

    /**
     *
     * 查询志愿列表
     */
    List<VolunteerFormDetailsVo> selectVolunteerFormDetailsByVo(@Param("voId") Integer voId,@Param("paragraphId") Integer paragraphId);
    /**
     * 查询志愿详情
     *
     * @return 志愿详情
     */
    VolunteerFormDetails selectVolunteerFormDetailsByOrder(Integer order);

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
    int updateVolunteerFormDetails(VolunteerFormDetails volunteerFormDetails);

    /**
     * 删除志愿详情
     * 
     * @param id 志愿详情主键
     * @return 结果
     */
    int deleteVolunteerFormDetailsById(Long id);
}
