package com.ruoyi.gk.mapper;

import java.util.List;
import com.ruoyi.gk.domain.SchoolScores;
import com.ruoyi.gk.domain.UserDetails;
import com.ruoyi.gk.domain.vo.RecommendVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 学校录取分数线Mapper接口
 *
 * @author cyz
 * @date 2024-07-16
 */
public interface SchoolScoresMapper
{

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
     * 删除学校录取分数线
     *
     * @param id 学校录取分数线主键
     * @return 结果
     */
    public int deleteSchoolScoresById(Long id);

    /**
     * 批量删除学校录取分数线
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolScoresByIds(Long[] ids);
}
