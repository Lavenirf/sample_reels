package com.ruoyi.gk.service.impl;

import java.util.List;

import com.ruoyi.gk.domain.vo.SchoolDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.mapper.SchoolDetailsMapper;
import com.ruoyi.gk.domain.SchoolDetails;
import com.ruoyi.gk.service.ISchoolDetailsService;

/**
 * 学校详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-03
 */
@Service
public class SchoolDetailsServiceImpl implements ISchoolDetailsService 
{
    @Autowired
    private SchoolDetailsMapper schoolDetailsMapper;

    /**
     * 查询学校详情
     * 
     * @param id 学校详情主键
     * @return 学校详情
     */
    @Override
    public SchoolDetailsVo selectSchoolDetailsById(Long id)
    {
        return schoolDetailsMapper.selectSchoolDetailsById(id);
    }

    /**
     * 查询学校详情列表
     * 
     * @param schoolDetails 学校详情
     * @return 学校详情
     */
    @Override
    public List<SchoolDetails> selectSchoolDetailsList(SchoolDetails schoolDetails)
    {
        return schoolDetailsMapper.selectSchoolDetailsList(schoolDetails);
    }

    /**
     * 新增学校详情
     * 
     * @param schoolDetails 学校详情
     * @return 结果
     */
    @Override
    public int insertSchoolDetails(SchoolDetails schoolDetails)
    {
        return schoolDetailsMapper.insertSchoolDetails(schoolDetails);
    }

    /**
     * 修改学校详情
     * 
     * @param schoolDetails 学校详情
     * @return 结果
     */
    @Override
    public int updateSchoolDetails(SchoolDetails schoolDetails)
    {
        return schoolDetailsMapper.updateSchoolDetails(schoolDetails);
    }

    /**
     * 批量删除学校详情
     * 
     * @param ids 需要删除的学校详情主键
     * @return 结果
     */
    @Override
    public int deleteSchoolDetailsByIds(Long[] ids)
    {
        return schoolDetailsMapper.deleteSchoolDetailsByIds(ids);
    }

    /**
     * 删除学校详情信息
     * 
     * @param id 学校详情主键
     * @return 结果
     */
    @Override
    public int deleteSchoolDetailsById(Long id)
    {
        return schoolDetailsMapper.deleteSchoolDetailsById(id);
    }
}
