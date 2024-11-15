package com.ruoyi.gk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.mapper.HollandPersonalityMapper;
import com.ruoyi.gk.domain.HollandPersonality;
import com.ruoyi.gk.service.IHollandPersonalityService;

/**
 * 霍兰德职业兴趣测试人格Service业务层处理
 * 
 * @author cyz
 * @date 2024-06-04
 */
@Service
public class HollandPersonalityServiceImpl implements IHollandPersonalityService 
{
    @Autowired
    private HollandPersonalityMapper hollandPersonalityMapper;

    /**
     * 查询霍兰德职业兴趣测试人格
     * 
     * @param personalityId 霍兰德职业兴趣测试人格主键
     * @return 霍兰德职业兴趣测试人格
     */
    @Override
    public HollandPersonality selectHollandPersonalityByPersonalityId(Long personalityId)
    {
        return hollandPersonalityMapper.selectHollandPersonalityByPersonalityId(personalityId);
    }

    /**
     * 查询霍兰德职业兴趣测试人格列表
     * 
     * @param hollandPersonality 霍兰德职业兴趣测试人格
     * @return 霍兰德职业兴趣测试人格
     */
    @Override
    public List<HollandPersonality> selectHollandPersonalityList(HollandPersonality hollandPersonality)
    {
        return hollandPersonalityMapper.selectHollandPersonalityList(hollandPersonality);
    }

    /**
     * 新增霍兰德职业兴趣测试人格
     * 
     * @param hollandPersonality 霍兰德职业兴趣测试人格
     * @return 结果
     */
    @Override
    public int insertHollandPersonality(HollandPersonality hollandPersonality)
    {
        hollandPersonality.setCreateTime(DateUtils.getNowDate());
        return hollandPersonalityMapper.insertHollandPersonality(hollandPersonality);
    }

    /**
     * 修改霍兰德职业兴趣测试人格
     * 
     * @param hollandPersonality 霍兰德职业兴趣测试人格
     * @return 结果
     */
    @Override
    public int updateHollandPersonality(HollandPersonality hollandPersonality)
    {
        hollandPersonality.setUpdateTime(DateUtils.getNowDate());
        return hollandPersonalityMapper.updateHollandPersonality(hollandPersonality);
    }

    /**
     * 批量删除霍兰德职业兴趣测试人格
     * 
     * @param personalityIds 需要删除的霍兰德职业兴趣测试人格主键
     * @return 结果
     */
    @Override
    public int deleteHollandPersonalityByPersonalityIds(Long[] personalityIds)
    {
        return hollandPersonalityMapper.deleteHollandPersonalityByPersonalityIds(personalityIds);
    }

    /**
     * 删除霍兰德职业兴趣测试人格信息
     * 
     * @param personalityId 霍兰德职业兴趣测试人格主键
     * @return 结果
     */
    @Override
    public int deleteHollandPersonalityByPersonalityId(Long personalityId)
    {
        return hollandPersonalityMapper.deleteHollandPersonalityByPersonalityId(personalityId);
    }
}
