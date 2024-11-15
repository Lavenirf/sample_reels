package com.ruoyi.gk.service;

import java.util.List;
import com.ruoyi.gk.domain.RecommendedMajors;

/**
 * 推荐专业Service接口
 * 
 * @author cyz
 * @date 2024-07-19
 */
public interface IRecommendedMajorsService 
{
    /**
     * 查询推荐专业
     * 
     * @param id 推荐专业主键
     * @return 推荐专业
     */
    public RecommendedMajors selectRecommendedMajorsById(Long id);

    /**
     * 查询推荐专业列表
     * 
     * @param recommendedMajors 推荐专业
     * @return 推荐专业集合
     */
    public List<RecommendedMajors> selectRecommendedMajorsList(RecommendedMajors recommendedMajors);

    /**
     * 新增推荐专业
     * 
     * @param recommendedMajors 推荐专业
     * @return 结果
     */
    public int insertRecommendedMajors(RecommendedMajors recommendedMajors);

    /**
     * 修改推荐专业
     * 
     * @param recommendedMajors 推荐专业
     * @return 结果
     */
    public int updateRecommendedMajors(RecommendedMajors recommendedMajors);

    /**
     * 批量删除推荐专业
     * 
     * @param ids 需要删除的推荐专业主键集合
     * @return 结果
     */
    public int deleteRecommendedMajorsByIds(Long[] ids);

    /**
     * 删除推荐专业信息
     * 
     * @param id 推荐专业主键
     * @return 结果
     */
    public int deleteRecommendedMajorsById(Long id);
}
