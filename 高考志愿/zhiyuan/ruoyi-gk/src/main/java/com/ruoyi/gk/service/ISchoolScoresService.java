package com.ruoyi.gk.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.ruoyi.gk.domain.*;
import com.ruoyi.gk.domain.vo.ProfessionallyRecommendedVo;
import com.ruoyi.gk.domain.vo.RecommendVo;

/**
 * 学校录取分数线Service接口
 *
 * @author cyz
 * @date 2024-07-16
 */
public interface ISchoolScoresService
{
    ProfessionallyRecommendedVo selectSpecializedListAsync(RecommendVo recommendVo);

    public List<SchoolScores> selectPosition(RecommendVo recommendVo);

    /**
     * 查询学校录取分数线
     *
     * @param id 学校录取分数线主键
     * @return 学校录取分数线
     */
    public SchoolScores selectSchoolScoresById(Long id);

    /**
     * 查询学校录取分数线列表
     *
     * @param schoolScores 学校录取分数线
     * @return 学校录取分数线集合
     */
    public List<SchoolScores> selectSchoolScoresList(SchoolScores schoolScores);

    /**
     * 新增学校录取分数线
     *
     * @param schoolScores 学校录取分数线
     * @return 结果
     */
    public int insertSchoolScores(SchoolScores schoolScores);

    /**
     * 修改学校录取分数线
     *
     * @param schoolScores 学校录取分数线
     * @return 结果
     */
    public int updateSchoolScores(SchoolScores schoolScores);

    /**
     * 批量删除学校录取分数线
     *
     * @param ids 需要删除的学校录取分数线主键集合
     * @return 结果
     */
    public int deleteSchoolScoresByIds(Long[] ids);

    /**
     * 删除学校录取分数线信息
     *
     * @param id 学校录取分数线主键
     * @return 结果
     */
    public int deleteSchoolScoresById(Long id);
}
