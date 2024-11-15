package com.ruoyi.gk.mapper;

import com.ruoyi.gk.domain.SchoolSpecial;

import java.util.List;

/**
 * 学校专业Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-22
 */
public interface SchoolSpecialMapper 
{
    /**
     * 查询学校专业
     * 
     * @param specialId 学校专业主键
     * @return 学校专业
     */
    public SchoolSpecial selectSchoolSpecialBySpecialId(Long specialId);

    /**
     * 查询学校专业列表
     * 
     * @param schoolSpecial 学校专业
     * @return 学校专业集合
     */
    public List<SchoolSpecial> selectSchoolSpecialList(SchoolSpecial schoolSpecial);

    /**
     * 新增学校专业
     * 
     * @param schoolSpecial 学校专业
     * @return 结果
     */
    public int insertSchoolSpecial(SchoolSpecial schoolSpecial);

    /**
     * 修改学校专业
     * 
     * @param schoolSpecial 学校专业
     * @return 结果
     */
    public int updateSchoolSpecial(SchoolSpecial schoolSpecial);

    /**
     * 删除学校专业
     * 
     * @param specialId 学校专业主键
     * @return 结果
     */
    public int deleteSchoolSpecialBySpecialId(Long specialId);

    /**
     * 批量删除学校专业
     * 
     * @param specialIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolSpecialBySpecialIds(Long[] specialIds);

    List<SchoolSpecial> selectSpecialByName(String name);
}
