package com.ruoyi.gk.service.impl;

import java.util.List;

import com.ruoyi.gk.service.ISchoolProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.domain.SchoolProfile;
import com.ruoyi.gk.mapper.SchoolProfileMapper;
/**
 * schoolProfileService业务层处理
 * 
 * @author wbl
 * @date 2024-08-03
 */
@Service
public class SchoolProfileServiceImpl implements ISchoolProfileService
{
    @Autowired
    private SchoolProfileMapper schoolProfileMapper;

    /**
     * 查询schoolProfile
     * 
     * @param id schoolProfile主键
     * @return schoolProfile
     */
    @Override
    public SchoolProfile selectSchoolProfileById(Long id)
    {
        return schoolProfileMapper.selectSchoolProfileById(id);
    }

    /**
     * 查询schoolProfile列表
     * 
     * @param schoolProfile schoolProfile
     * @return schoolProfile
     */
    @Override
    public List<SchoolProfile> selectSchoolProfileList(SchoolProfile schoolProfile)
    {
        return schoolProfileMapper.selectSchoolProfileList(schoolProfile);
    }

    /**
     * 新增schoolProfile
     * 
     * @param schoolProfile schoolProfile
     * @return 结果
     */
    @Override
    public int insertSchoolProfile(SchoolProfile schoolProfile)
    {
        return schoolProfileMapper.insertSchoolProfile(schoolProfile);
    }

    /**
     * 修改schoolProfile
     * 
     * @param schoolProfile schoolProfile
     * @return 结果
     */
    @Override
    public int updateSchoolProfile(SchoolProfile schoolProfile)
    {
        return schoolProfileMapper.updateSchoolProfile(schoolProfile);
    }

    /**
     * 批量删除schoolProfile
     * 
     * @param ids 需要删除的schoolProfile主键
     * @return 结果
     */
    @Override
    public int deleteSchoolProfileByIds(Long[] ids)
    {
        return schoolProfileMapper.deleteSchoolProfileByIds(ids);
    }

    /**
     * 删除schoolProfile信息
     * 
     * @param id schoolProfile主键
     * @return 结果
     */
    @Override
    public int deleteSchoolProfileById(Long id)
    {
        return schoolProfileMapper.deleteSchoolProfileById(id);
    }
}
