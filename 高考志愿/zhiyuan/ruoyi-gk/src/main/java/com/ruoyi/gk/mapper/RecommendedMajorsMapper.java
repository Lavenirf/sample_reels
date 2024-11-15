package com.ruoyi.gk.mapper;

import java.util.List;
import com.ruoyi.gk.domain.RecommendedMajors;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * 推荐专业Mapper接口
 *
 * @author cyz
 * @date 2024-07-19
 */
public interface RecommendedMajorsMapper
{
    /**
     * 查询推荐专业
     *
     * @param id 推荐专业主键
     * @return 推荐专业
     */
    public RecommendedMajors selectRecommendedMajorsById(Long id);


    public List<RecommendedMajors> selectRecommendedMajorschong(RecommendedMajors recommendedMajors);
    public List<RecommendedMajors> selectRecommendedMajorswen(RecommendedMajors recommendedMajors);
    public List<RecommendedMajors> selectRecommendedMajorsbao(RecommendedMajors recommendedMajors);
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
     * 删除推荐专业
     *
     * @param id 推荐专业主键
     * @return 结果
     */
    public int deleteRecommendedMajorsById(Long id);

    /**
     * 批量删除推荐专业
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRecommendedMajorsByIds(Long[] ids);

    public int deleteRecommendedMajorsAll();
    @Delete("delete  from recommended_majors where user_id = #{userId}")
    public int delectCollegesByuserId(@Param("userId") Long userId);
}
