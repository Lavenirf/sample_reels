package com.ruoyi.gk.service;

import java.util.List;
import com.ruoyi.gk.domain.HollandPersonality;

/**
 * 霍兰德职业兴趣测试人格Service接口
 * 
 * @author cyz
 * @date 2024-06-04
 */
public interface IHollandPersonalityService 
{
    /**
     * 查询霍兰德职业兴趣测试人格
     * 
     * @param personalityId 霍兰德职业兴趣测试人格主键
     * @return 霍兰德职业兴趣测试人格
     */
    public HollandPersonality selectHollandPersonalityByPersonalityId(Long personalityId);

    /**
     * 查询霍兰德职业兴趣测试人格列表
     * 
     * @param hollandPersonality 霍兰德职业兴趣测试人格
     * @return 霍兰德职业兴趣测试人格集合
     */
    public List<HollandPersonality> selectHollandPersonalityList(HollandPersonality hollandPersonality);

    /**
     * 新增霍兰德职业兴趣测试人格
     * 
     * @param hollandPersonality 霍兰德职业兴趣测试人格
     * @return 结果
     */
    public int insertHollandPersonality(HollandPersonality hollandPersonality);

    /**
     * 修改霍兰德职业兴趣测试人格
     * 
     * @param hollandPersonality 霍兰德职业兴趣测试人格
     * @return 结果
     */
    public int updateHollandPersonality(HollandPersonality hollandPersonality);

    /**
     * 批量删除霍兰德职业兴趣测试人格
     * 
     * @param personalityIds 需要删除的霍兰德职业兴趣测试人格主键集合
     * @return 结果
     */
    public int deleteHollandPersonalityByPersonalityIds(Long[] personalityIds);

    /**
     * 删除霍兰德职业兴趣测试人格信息
     * 
     * @param personalityId 霍兰德职业兴趣测试人格主键
     * @return 结果
     */
    public int deleteHollandPersonalityByPersonalityId(Long personalityId);
}
