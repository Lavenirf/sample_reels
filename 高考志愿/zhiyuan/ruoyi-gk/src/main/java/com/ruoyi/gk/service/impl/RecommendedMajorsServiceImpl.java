package com.ruoyi.gk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.mapper.RecommendedMajorsMapper;
import com.ruoyi.gk.domain.RecommendedMajors;
import com.ruoyi.gk.service.IRecommendedMajorsService;

/**
 * 推荐专业Service业务层处理
 *
 * @author cyz
 * @date 2024-07-19
 */
@Service
public class RecommendedMajorsServiceImpl implements IRecommendedMajorsService
{
    @Autowired
    private RecommendedMajorsMapper recommendedMajorsMapper;

    /**
     * 查询推荐专业
     *
     * @param id 推荐专业主键
     * @return 推荐专业
     */
    @Override
    public RecommendedMajors selectRecommendedMajorsById(Long id)
    {
        return recommendedMajorsMapper.selectRecommendedMajorsById(id);
    }

    /**
     * 查询推荐专业列表
     *
     * @param recommendedMajors 推荐专业
     * @return 推荐专业
     */
    @Override
    public List<RecommendedMajors> selectRecommendedMajorsList(RecommendedMajors recommendedMajors)
    {
        return recommendedMajorsMapper.selectRecommendedMajorsList(recommendedMajors);
    }

    /**
     * 新增推荐专业
     *
     * @param recommendedMajors 推荐专业
     * @return 结果
     */
    @Override
    public int insertRecommendedMajors(RecommendedMajors recommendedMajors)
    {
        return recommendedMajorsMapper.insertRecommendedMajors(recommendedMajors);
    }

    /**
     * 修改推荐专业
     *
     * @param recommendedMajors 推荐专业
     * @return 结果
     */
    @Override
    public int updateRecommendedMajors(RecommendedMajors recommendedMajors)
    {
        return recommendedMajorsMapper.updateRecommendedMajors(recommendedMajors);
    }

    /**
     * 批量删除推荐专业
     *
     * @param ids 需要删除的推荐专业主键
     * @return 结果
     */
    @Override
    public int deleteRecommendedMajorsByIds(Long[] ids)
    {
        return recommendedMajorsMapper.deleteRecommendedMajorsByIds(ids);
    }

    /**
     * 删除推荐专业信息
     *
     * @param id 推荐专业主键
     * @return 结果
     */
    @Override
    public int deleteRecommendedMajorsById(Long id)
    {
        return recommendedMajorsMapper.delectCollegesByuserId(id);
    }
}
