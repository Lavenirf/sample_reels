package com.ruoyi.gk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.gk.domain.VolunteerForm;
import com.ruoyi.gk.mapper.VolunteerFormMapper;
import com.ruoyi.gk.service.IVolunteerFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 志愿Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-16
 */
@Service
public class VolunteerFormServiceImpl implements IVolunteerFormService
{
    @Autowired
    private VolunteerFormMapper volunteerFormMapper;

    /**
     * 查询志愿
     * 
     * @param id 志愿主键
     * @return 志愿
     */
    @Override
    public List<VolunteerForm> selectVolunteerFormByUserId(Long id)
    {
        return volunteerFormMapper.selectVolunteerFormByUserId(id);
    }

    @Override
    public VolunteerForm selectVolunteerFormById(Long id) {
        return volunteerFormMapper.selectVolunteerFormById(id);
    }

    /**
     * 查询志愿列表
     * 
     * @param volunteerForm 志愿
     * @return 志愿
     */
    @Override
    public List<VolunteerForm> selectVolunteerFormList(VolunteerForm volunteerForm)
    {
        return volunteerFormMapper.selectVolunteerFormList(volunteerForm);
    }

    /**
     * 新增志愿
     * 
     * @param volunteerForm 志愿
     * @return 结果
     */
    @Override
    public Long insertVolunteerForm(VolunteerForm volunteerForm)
    {
        volunteerForm.setCreateTime(DateUtils.getNowDate());
        volunteerFormMapper.insertVolunteerForm(volunteerForm);
        return volunteerForm.getId();
    }

    /**
     * 修改志愿
     * 
     * @param volunteerForm 志愿
     * @return 结果
     */
    @Override
    public int updateVolunteerForm(VolunteerForm volunteerForm)
    {
        System.out.println(volunteerForm);
        return volunteerFormMapper.updateVolunteerForm(volunteerForm);
    }

    /**
     * 删除志愿信息
     * 
     * @param id 志愿主键
     * @return 结果
     */
    @Override
    public int deleteVolunteerFormById(Long id)
    {
        return volunteerFormMapper.deleteVolunteerFormById(id);
    }
}
