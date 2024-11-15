package com.ruoyi.gk.service;

import java.util.List;
import com.ruoyi.gk.domain.Colleges;

/**
 * 推荐院校Service接口
 *
 * @author ruoyi
 * @date 2024-07-18
 */
public interface ICollegesService
{
    public Colleges getCollegeFromRedis(Colleges college);
    public void saveCollegeToRedis(Colleges college);
    /**
     * 查询推荐院校
     *
     * @param id 推荐院校主键
     * @return 推荐院校
     */
    public Colleges selectCollegesById(Long id);

    /**
     * 查询推荐院校列表
     *
     * @param colleges 推荐院校
     * @return 推荐院校集合
     */
    public List<Colleges> selectCollegesList(Colleges colleges);

    /**
     * 新增推荐院校
     *
     * @param colleges 推荐院校
     * @return 结果
     */
    public int insertColleges(Colleges colleges);

    /**
     * 修改推荐院校
     *
     * @param colleges 推荐院校
     * @return 结果
     */
    public int updateColleges(Colleges colleges);

    /**
     * 批量删除推荐院校
     *
     * @param id 需要删除的推荐院校Userid
     * @return 结果
     */
    public int deleteCollegesByUserId(Long id);

    /**
     * 批量删除推荐院校
     *
     * @param ids 需要删除的推荐院校主键集合
     * @return 结果
     */
    public int deleteCollegesByIds(Long[] ids);

    /**
     * 删除推荐院校信息
     *
     * @param id 推荐院校主键
     * @return 结果
     */
    public int deleteCollegesById(Long id);
}
