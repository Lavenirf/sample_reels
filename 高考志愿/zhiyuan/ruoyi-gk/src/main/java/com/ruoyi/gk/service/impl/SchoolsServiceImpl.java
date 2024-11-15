package com.ruoyi.gk.service.impl;

import java.util.List;

import com.ruoyi.gk.domain.Schools;
import com.ruoyi.gk.domain.vo.PopularSchoolVo;
import com.ruoyi.gk.domain.vo.SchoolFractionVo;
import com.ruoyi.gk.mapper.SchoolsMapper;
import com.ruoyi.gk.service.ISchoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 学校Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
@Service
public class SchoolsServiceImpl implements ISchoolsService
{
    @Autowired
    private SchoolsMapper schoolsMapper;


    /**
     * 查询学校
     * 
     * @param id 学校主键
     * @return 学校
     */
    @Override
    public Schools selectSchoolsById(Long id)
    {
        return schoolsMapper.selectSchoolsById(id);
    }

    /**
     * 查询学校列表
     * 
     * @param schools 学校
     * @return 学校
     */
    @Override
    public List<Schools> selectSchoolsList(Schools schools)
    {
        return schoolsMapper.selectSchoolsList(schools);
    }

    /**
     * 查询学校列表 by 地区
     *
     * @return 学校
     */
    @Override
    public List<SchoolFractionVo> selectSchoolsListByLocal(String localName,String typeName,String levelName,String name,Integer year,String provinceName)
    {
        return schoolsMapper.selectSchoolsListByLocal(localName,typeName,levelName,name,year,provinceName);
    }

    /**
     * 新增学校
     * 
     * @param schools 学校
     * @return 结果
     */
    @Override
    public int insertSchools(Schools schools)
    {
        return schoolsMapper.insertSchools(schools);
    }

    /**
     * 修改学校
     * 
     * @param schools 学校
     * @return 结果
     */
    @Override
    public int updateSchools(Schools schools)
    {
        return schoolsMapper.updateSchools(schools);
    }

    /**
     * 批量删除学校
     * 
     * @param ids 需要删除的学校主键
     * @return 结果
     */
    @Override
    public int deleteSchoolsByIds(Long[] ids)
    {
        return schoolsMapper.deleteSchoolsByIds(ids);
    }

    /**
     * 删除学校信息
     * 
     * @param id 学校主键
     * @return 结果
     */
    @Override
    public int deleteSchoolsById(Long id)
    {
        return schoolsMapper.deleteSchoolsById(id);
    }

    /**
     * 查询热门学校
     */
    @Override
    public List<PopularSchoolVo> getPopularSchool(String local) {
        return schoolsMapper.getPopularSchool(local);
    }

    /**
     * 查询学校
     * @param name
     * @return
     */
    @Override
    public List<PopularSchoolVo> selectSchoolsListByName(String name) {
        return schoolsMapper.selectSchoolListByName(name);
    }

    /**
     * 批量查询学校
     * @param ids
     * @return
     */

    @Override
    public List<PopularSchoolVo> selectSchoolsByIds(List<Integer> ids) {
        return schoolsMapper.selectSchoolByIds(ids);
    }
}
