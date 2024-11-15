package com.ruoyi.gk.service.impl;

import java.util.List;

import com.ruoyi.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.mapper.SchoolMajorScoresMapper;
import com.ruoyi.gk.domain.SchoolMajorScores;
import com.ruoyi.gk.service.ISchoolMajorScoresService;

/**
 * 学校各专业录取分数线及位次Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-18
 */
@Service
public class SchoolMajorScoresServiceImpl implements ISchoolMajorScoresService
{
    @Autowired
    private SchoolMajorScoresMapper schoolMajorScoresMapper;
    @Autowired
    private RedisCache redisCache;
    /**
     * 查询学校各专业录取分数线及位次
     *
     * @param mcid 学校各专业录取分数线及位次主键
     * @return 学校各专业录取分数线及位次
     */
    @Override
    public SchoolMajorScores selectSchoolMajorScoresByMcid(Long mcid)
    {
        return schoolMajorScoresMapper.selectSchoolMajorScoresByMcid(mcid);
    }

    /**
     * 查询学校各专业录取分数线及位次列表
     *
     * @param schoolMajorScores 学校各专业录取分数线及位次
     * @return 学校各专业录取分数线及位次
     */
    @Override
    public List<SchoolMajorScores> selectSchoolMajorScoresList(SchoolMajorScores schoolMajorScores)
    {
        return schoolMajorScoresMapper.selectSchoolMajorScoresList(schoolMajorScores);
    }
    @Override
    public List<SchoolMajorScores> selectSchoolMajorScoresschoolList(SchoolMajorScores schoolMajorScores)
    {// 构建缓存键名
        //String cacheKey = "school_major_scores:" + schoolMajorScores.getSchoolId()+"name:"+schoolMajorScores.getLocalProvinceName();

        // 尝试从Redis中获取数据
        //List<SchoolMajorScores> cachedScores = redisCache.getCacheList(cacheKey);

           // System.out.println(redisCache.getCacheObject(cacheKey).toString());


        return schoolMajorScoresMapper.selectSchoolMajorScoresschoolList(schoolMajorScores);
    }

    /**
     * 新增学校各专业录取分数线及位次
     *
     * @param schoolMajorScores 学校各专业录取分数线及位次
     * @return 结果
     */
    @Override
    public int insertSchoolMajorScores(SchoolMajorScores schoolMajorScores)
    {
        return schoolMajorScoresMapper.insertSchoolMajorScores(schoolMajorScores);
    }

    /**
     * 修改学校各专业录取分数线及位次
     *
     * @param schoolMajorScores 学校各专业录取分数线及位次
     * @return 结果
     */
    @Override
    public int updateSchoolMajorScores(SchoolMajorScores schoolMajorScores)
    {
        return schoolMajorScoresMapper.updateSchoolMajorScores(schoolMajorScores);
    }

    /**
     * 批量删除学校各专业录取分数线及位次
     *
     * @param mcids 需要删除的学校各专业录取分数线及位次主键
     * @return 结果
     */
    @Override
    public int deleteSchoolMajorScoresByMcids(Long[] mcids)
    {
        return schoolMajorScoresMapper.deleteSchoolMajorScoresByMcids(mcids);
    }



    /**
     * 删除学校各专业录取分数线及位次信息
     *
     * @param mcid 学校各专业录取分数线及位次主键
     * @return 结果
     */
    @Override
    public int deleteSchoolMajorScoresByMcid(Long mcid)
    {
        return schoolMajorScoresMapper.deleteSchoolMajorScoresByMcid(mcid);
    }
}
