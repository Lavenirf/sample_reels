package com.ruoyi.gk.service.impl;

import java.util.List;

import com.ruoyi.gk.domain.SchoolNews;
import com.ruoyi.gk.service.ISchoolNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.mapper.SchoolNewsMapper;
/**
 * newsService业务层处理
 * 
 * @author wbl
 * @date 2024-08-03
 */
@Service
public class SchoolNewsServiceImpl implements ISchoolNewsService
{
    @Autowired
    private SchoolNewsMapper schoolNewsMapper;

    /**
     * 查询news
     * 
     * @param id news主键
     * @return news
     */
    @Override
    public SchoolNews selectSchoolNewsById(Long id)
    {
        return schoolNewsMapper.selectSchoolNewsById(id);
    }

    /**
     * 查询news列表
     * 
     * @param schoolNews news
     * @return news
     */
    @Override
    public List<SchoolNews> selectSchoolNewsList(SchoolNews schoolNews)
    {
        return schoolNewsMapper.selectSchoolNewsList(schoolNews);
    }

    /**
     * 新增news
     * 
     * @param schoolNews news
     * @return 结果
     */
    @Override
    public int insertSchoolNews(SchoolNews schoolNews)
    {
        return schoolNewsMapper.insertSchoolNews(schoolNews);
    }

    /**
     * 修改news
     * 
     * @param schoolNews news
     * @return 结果
     */
    @Override
    public int updateSchoolNews(SchoolNews schoolNews)
    {
        return schoolNewsMapper.updateSchoolNews(schoolNews);
    }

    /**
     * 批量删除news
     * 
     * @param ids 需要删除的news主键
     * @return 结果
     */
    @Override
    public int deleteSchoolNewsByIds(Long[] ids)
    {
        return schoolNewsMapper.deleteSchoolNewsByIds(ids);
    }

    /**
     * 删除news信息
     * 
     * @param id news主键
     * @return 结果
     */
    @Override
    public int deleteSchoolNewsById(Long id)
    {
        return schoolNewsMapper.deleteSchoolNewsById(id);
    }
}
