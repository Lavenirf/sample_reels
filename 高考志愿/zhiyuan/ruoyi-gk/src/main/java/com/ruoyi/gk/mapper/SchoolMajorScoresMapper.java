package com.ruoyi.gk.mapper;

import java.util.List;
import com.ruoyi.gk.domain.SchoolMajorScores;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Select;

/**
 * 学校各专业录取分数线及位次Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-18
 */
public interface SchoolMajorScoresMapper
{
    /**
     * 查询学校各专业录取分数线及位次
     *
     * @param mcid 学校各专业录取分数线及位次主键
     * @return 学校各专业录取分数线及位次
     */
    public SchoolMajorScores selectSchoolMajorScoresByMcid(Long mcid);

    /**
     * 查询学校各专业录取分数线及位次列表
     *
     * @param schoolMajorScores 学校各专业录取分数线及位次
     * @return 学校各专业录取分数线及位次集合
     */
    public List<SchoolMajorScores> selectSchoolMajorScoresList(SchoolMajorScores schoolMajorScores);

    public List<SchoolMajorScores> selectSchoolMajorScoresschoolList(SchoolMajorScores schoolMajorScores);
    /**
     * 新增学校各专业录取分数线及位次
     *
     * @param schoolMajorScores 学校各专业录取分数线及位次
     * @return 结果
     */
    public int insertSchoolMajorScores(SchoolMajorScores schoolMajorScores);

    /**
     * 修改学校各专业录取分数线及位次
     *
     * @param schoolMajorScores 学校各专业录取分数线及位次
     * @return 结果
     */
    public int updateSchoolMajorScores(SchoolMajorScores schoolMajorScores);

    /**
     * 删除学校各专业录取分数线及位次
     *
     * @param mcid 学校各专业录取分数线及位次主键
     * @return 结果
     */
    public int deleteSchoolMajorScoresByMcid(Long mcid);

    /**
     * 批量删除学校各专业录取分数线及位次
     *
     * @param mcids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolMajorScoresByMcids(Long[] mcids);


    List<SchoolMajorScores> selectSchoolMajorScoresListBySchoolIds(
            @Param("schoolIds") List<Long> schoolIds,
            @Param("localProvinceName") String localProvinceName,
            @Param("level2Name") String level2Name
    );

}
