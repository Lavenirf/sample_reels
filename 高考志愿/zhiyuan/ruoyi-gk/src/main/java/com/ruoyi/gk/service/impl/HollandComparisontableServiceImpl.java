package com.ruoyi.gk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.mapper.HollandComparisontableMapper;
import com.ruoyi.gk.domain.HollandComparisontable;
import com.ruoyi.gk.service.IHollandComparisontableService;

/**
 * 霍兰德职业兴趣测试结果与职业匹配对照Service业务层处理
 * 
 * @author cyz
 * @date 2024-06-05
 */
@Service
public class HollandComparisontableServiceImpl implements IHollandComparisontableService 
{
    @Autowired
    private HollandComparisontableMapper hollandComparisontableMapper;

    /**
     * 查询霍兰德职业兴趣测试结果与职业匹配对照
     * 
     * @param comparisontableId 霍兰德职业兴趣测试结果与职业匹配对照主键
     * @return 霍兰德职业兴趣测试结果与职业匹配对照
     */
    @Override
    public HollandComparisontable selectHollandComparisontableByComparisontableId(Long comparisontableId)
    {
        return hollandComparisontableMapper.selectHollandComparisontableByComparisontableId(comparisontableId);
    }

    /**
     * 查询霍兰德职业兴趣测试结果与职业匹配对照列表
     * 
     * @param hollandComparisontable 霍兰德职业兴趣测试结果与职业匹配对照
     * @return 霍兰德职业兴趣测试结果与职业匹配对照
     */
    @Override
    public List<HollandComparisontable> selectHollandComparisontableList(HollandComparisontable hollandComparisontable)
    {
        return hollandComparisontableMapper.selectHollandComparisontableList(hollandComparisontable);
    }

    /**
     * 新增霍兰德职业兴趣测试结果与职业匹配对照
     * 
     * @param hollandComparisontable 霍兰德职业兴趣测试结果与职业匹配对照
     * @return 结果
     */
    @Override
    public int insertHollandComparisontable(HollandComparisontable hollandComparisontable)
    {
        hollandComparisontable.setCreateTime(DateUtils.getNowDate());
        return hollandComparisontableMapper.insertHollandComparisontable(hollandComparisontable);
    }

    /**
     * 修改霍兰德职业兴趣测试结果与职业匹配对照
     * 
     * @param hollandComparisontable 霍兰德职业兴趣测试结果与职业匹配对照
     * @return 结果
     */
    @Override
    public int updateHollandComparisontable(HollandComparisontable hollandComparisontable)
    {
        hollandComparisontable.setUpdateTime(DateUtils.getNowDate());
        return hollandComparisontableMapper.updateHollandComparisontable(hollandComparisontable);
    }

    /**
     * 批量删除霍兰德职业兴趣测试结果与职业匹配对照
     * 
     * @param comparisontableIds 需要删除的霍兰德职业兴趣测试结果与职业匹配对照主键
     * @return 结果
     */
    @Override
    public int deleteHollandComparisontableByComparisontableIds(Long[] comparisontableIds)
    {
        return hollandComparisontableMapper.deleteHollandComparisontableByComparisontableIds(comparisontableIds);
    }

    /**
     * 删除霍兰德职业兴趣测试结果与职业匹配对照信息
     * 
     * @param comparisontableId 霍兰德职业兴趣测试结果与职业匹配对照主键
     * @return 结果
     */
    @Override
    public int deleteHollandComparisontableByComparisontableId(Long comparisontableId)
    {
        return hollandComparisontableMapper.deleteHollandComparisontableByComparisontableId(comparisontableId);
    }
}
