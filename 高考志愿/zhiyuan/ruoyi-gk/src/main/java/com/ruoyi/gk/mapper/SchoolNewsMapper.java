package com.ruoyi.gk.mapper;

import com.ruoyi.gk.domain.SchoolNews;

import java.util.List;

/**
 * newsMapper接口
 * 
 * @author wbl
 * @date 2024-08-03
 */
public interface SchoolNewsMapper 
{
    /**
     * 查询news
     * 
     * @param id news主键
     * @return news
     */
    public SchoolNews selectSchoolNewsById(Long id);

    /**
     * 查询news列表
     * 
     * @param schoolNews news
     * @return news集合
     */
    public List<SchoolNews> selectSchoolNewsList(SchoolNews schoolNews);

    /**
     * 新增news
     * 
     * @param schoolNews news
     * @return 结果
     */
    public int insertSchoolNews(SchoolNews schoolNews);

    /**
     * 修改news
     * 
     * @param schoolNews news
     * @return 结果
     */
    public int updateSchoolNews(SchoolNews schoolNews);

    /**
     * 删除news
     * 
     * @param id news主键
     * @return 结果
     */
    public int deleteSchoolNewsById(Long id);

    /**
     * 批量删除news
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolNewsByIds(Long[] ids);
}
