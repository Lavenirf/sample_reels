package com.ruoyi.gk.service;

import com.ruoyi.gk.domain.Schools;
import com.ruoyi.gk.domain.vo.PopularSchoolVo;
import com.ruoyi.gk.domain.vo.SchoolFractionVo;

import java.util.List;


/**
 * 学校Service接口
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
public interface ISchoolsService 
{
    /**
     * 查询学校
     * 
     * @param id 学校主键
     * @return 学校
     */
    public Schools selectSchoolsById(Long id);

    /**
     * 查询学校列表
     * 
     * @param schools 学校
     * @return 学校集合
     */
    public List<Schools> selectSchoolsList(Schools schools);

    /**
     * 查询学校列表by地区
     *
     * @return 学校集合
     */
    List<SchoolFractionVo> selectSchoolsListByLocal(String localName,String typeName,String levelName,String name,Integer year,String provinceName);
    /**
     * 新增学校
     * 
     * @param schools 学校
     * @return 结果
     */
    public int insertSchools(Schools schools);

    /**
     * 修改学校
     * 
     * @param schools 学校
     * @return 结果
     */
    public int updateSchools(Schools schools);

    /**
     * 批量删除学校
     * 
     * @param ids 需要删除的学校主键集合
     * @return 结果
     */
    public int deleteSchoolsByIds(Long[] ids);

    /**
     * 删除学校信息
     * 
     * @param id 学校主键
     * @return 结果
     */
    public int deleteSchoolsById(Long id);

    /**
     * 获取热门学校
     *
     * @return 结果
     */
    List<PopularSchoolVo> getPopularSchool(String local);

    /**
     *查询学校
     * @param name
     * @return
     */
    List<PopularSchoolVo> selectSchoolsListByName(String name);

    List<PopularSchoolVo> selectSchoolsByIds(List<Integer> ids);
}
