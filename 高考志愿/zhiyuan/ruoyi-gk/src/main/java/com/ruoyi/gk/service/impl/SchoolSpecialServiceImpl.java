package com.ruoyi.gk.service.impl;

import java.util.List;

import com.ruoyi.gk.domain.SchoolSpecial;
import com.ruoyi.gk.mapper.SchoolSpecialMapper;
import com.ruoyi.gk.service.ISchoolSpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学校专业Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-22
 */
@Service
public class SchoolSpecialServiceImpl implements ISchoolSpecialService
{
    @Autowired
    private SchoolSpecialMapper schoolSpecialMapper;

    /**
     * 查询学校专业
     * 
     * @param specialId 学校专业主键
     * @return 学校专业
     */
    @Override
    public SchoolSpecial selectSchoolSpecialBySpecialId(Long specialId)
    {
        return schoolSpecialMapper.selectSchoolSpecialBySpecialId(specialId);
    }

    /**
     * 查询学校专业列表
     * 
     * @param schoolSpecial 学校专业
     * @return 学校专业
     */
    @Override
    public List<SchoolSpecial> selectSchoolSpecialList(SchoolSpecial schoolSpecial)
    {
        return schoolSpecialMapper.selectSchoolSpecialList(schoolSpecial);
    }

    /**
     * 新增学校专业
     * 
     * @param schoolSpecial 学校专业
     * @return 结果
     */
    @Override
    public int insertSchoolSpecial(SchoolSpecial schoolSpecial)
    {
        return schoolSpecialMapper.insertSchoolSpecial(schoolSpecial);
    }

    /**
     * 修改学校专业
     * 
     * @param schoolSpecial 学校专业
     * @return 结果
     */
    @Override
    public int updateSchoolSpecial(SchoolSpecial schoolSpecial)
    {
        return schoolSpecialMapper.updateSchoolSpecial(schoolSpecial);
    }

    /**
     * 批量删除学校专业
     * 
     * @param specialIds 需要删除的学校专业主键
     * @return 结果
     */
    @Override
    public int deleteSchoolSpecialBySpecialIds(Long[] specialIds)
    {
        return schoolSpecialMapper.deleteSchoolSpecialBySpecialIds(specialIds);
    }

    /**
     * 删除学校专业信息
     * 
     * @param specialId 学校专业主键
     * @return 结果
     */
    @Override
    public int deleteSchoolSpecialBySpecialId(Long specialId)
    {
        return schoolSpecialMapper.deleteSchoolSpecialBySpecialId(specialId);
    }

    @Override
    public List<SchoolSpecial> selectSpecialByName(String name) {
        return schoolSpecialMapper.selectSpecialByName(name);
    }
}
