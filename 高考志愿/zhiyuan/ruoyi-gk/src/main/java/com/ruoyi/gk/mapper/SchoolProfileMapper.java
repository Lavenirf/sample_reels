package com.ruoyi.gk.mapper;

import java.util.List;
import com.ruoyi.gk.domain.SchoolProfile;
/**
 * schoolProfileMapper接口
 * 
 * @author wbl
 * @date 2024-08-03
 */
public interface SchoolProfileMapper 
{
    /**
     * 查询schoolProfile
     * 
     * @param id schoolProfile主键
     * @return schoolProfile
     */
    public SchoolProfile selectSchoolProfileById(Long id);

    /**
     * 查询schoolProfile列表
     * 
     * @param schoolProfile schoolProfile
     * @return schoolProfile集合
     */
    public List<SchoolProfile> selectSchoolProfileList(SchoolProfile schoolProfile);

    /**
     * 新增schoolProfile
     * 
     * @param schoolProfile schoolProfile
     * @return 结果
     */
    public int insertSchoolProfile(SchoolProfile schoolProfile);

    /**
     * 修改schoolProfile
     * 
     * @param schoolProfile schoolProfile
     * @return 结果
     */
    public int updateSchoolProfile(SchoolProfile schoolProfile);

    /**
     * 删除schoolProfile
     * 
     * @param id schoolProfile主键
     * @return 结果
     */
    public int deleteSchoolProfileById(Long id);

    /**
     * 批量删除schoolProfile
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolProfileByIds(Long[] ids);
}
