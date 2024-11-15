package com.ruoyi.gk.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ruoyi.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.mapper.CollegesMapper;
import com.ruoyi.gk.domain.Colleges;
import com.ruoyi.gk.service.ICollegesService;

/**
 * 推荐院校Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-18
 */
@Service
public class CollegesServiceImpl implements ICollegesService
{
    @Autowired
    private RedisCache redisCache;

    public void saveCollegeToRedis(Colleges college) {
        // 定义键名
        String key = "userId:" + college.getUserId()+"schoolId:"+college.getShoolId();

        // 存储对象到Redis
        redisCache.setCacheObject(key, college, 3600, TimeUnit.SECONDS);

        // 输出确认信息
        System.out.println("College saved to Redis with key: " + key);
    }

    public Colleges getCollegeFromRedis(Colleges college) {
        // 定义键名
        String key = "userId:" + college.getUserId()+"schoolId:"+college.getShoolId();

        // 从Redis中获取对象
        Colleges college1 = redisCache.getCacheObject(key);

        // 返回对象
        return college1;
    }

    @Autowired
    private CollegesMapper collegesMapper;

    /**
     * 查询推荐院校
     *
     * @param id 推荐院校主键
     * @return 推荐院校
     */
    @Override
    public Colleges selectCollegesById(Long id)
    {
        return collegesMapper.selectCollegesById(id);
    }

    /**
     * 查询推荐院校列表
     *
     * @param colleges 推荐院校
     * @return 推荐院校
     */
    @Override
    public List<Colleges> selectCollegesList(Colleges colleges)
    {
        return collegesMapper.selectCollegesList(colleges);
    }

    /**
     * 新增推荐院校
     *
     * @param colleges 推荐院校
     * @return 结果
     */
    @Override
    public int insertColleges(Colleges colleges)
    {
        return collegesMapper.insertColleges(colleges);
    }

    /**
     * 修改推荐院校
     *
     * @param colleges 推荐院校
     * @return 结果
     */
    @Override
    public int updateColleges(Colleges colleges)
    {
        return collegesMapper.updateColleges(colleges);
    }

    @Override
    public int deleteCollegesByUserId(Long id) {
        return collegesMapper.delectCollegesByuserId(id);
    }

    /**
     * 批量删除推荐院校
     *
     * @param ids 需要删除的推荐院校主键
     * @return 结果
     */
    @Override
    public int deleteCollegesByIds(Long[] ids)
    {
        return collegesMapper.deleteCollegesByIds(ids);
    }

    /**
     * 删除推荐院校信息
     *
     * @param id 推荐院校主键
     * @return 结果
     */
    @Override
    public int deleteCollegesById(Long id)
    {
        return collegesMapper.deleteCollegesById(id);
    }
}
