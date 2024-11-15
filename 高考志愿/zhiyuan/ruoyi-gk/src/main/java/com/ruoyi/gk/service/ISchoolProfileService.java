package com.ruoyi.gk.service;

import java.util.List;
import com.ruoyi.gk.domain.SchoolProfile;

/**
 * schoolProfileService接口
 * 
 * @author wbl
 * @date 2024-08-03
 */
public interface ISchoolProfileService 
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
     * 批量删除schoolProfile
     * 
     * @param ids 需要删除的schoolProfile主键集合
     * @return 结果
     */
    public int deleteSchoolProfileByIds(Long[] ids);

    /**
     * 删除schoolProfile信息
     * 
     * @param id schoolProfile主键
     * @return 结果
     */
    public int deleteSchoolProfileById(Long id);
}
