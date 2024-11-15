package com.ruoyi.gk.service;

import com.ruoyi.gk.domain.SchoolNews;

import java.util.List;

/**
 * newsService接口
 * 
 * @author wbl
 * @date 2024-08-03
 */
public interface ISchoolNewsService 
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
     * 批量删除news
     * 
     * @param ids 需要删除的news主键集合
     * @return 结果
     */
    public int deleteSchoolNewsByIds(Long[] ids);

    /**
     * 删除news信息
     * 
     * @param id news主键
     * @return 结果
     */
    public int deleteSchoolNewsById(Long id);
}
