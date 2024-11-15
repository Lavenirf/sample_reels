package com.ruoyi.gk.service;

import java.util.List;
import com.ruoyi.gk.domain.HollandComparisontable;

/**
 * 霍兰德职业兴趣测试结果与职业匹配对照Service接口
 * 
 * @author cyz
 * @date 2024-06-05
 */
public interface IHollandComparisontableService 
{
    /**
     * 查询霍兰德职业兴趣测试结果与职业匹配对照
     * 
     * @param comparisontableId 霍兰德职业兴趣测试结果与职业匹配对照主键
     * @return 霍兰德职业兴趣测试结果与职业匹配对照
     */
    public HollandComparisontable selectHollandComparisontableByComparisontableId(Long comparisontableId);

    /**
     * 查询霍兰德职业兴趣测试结果与职业匹配对照列表
     * 
     * @param hollandComparisontable 霍兰德职业兴趣测试结果与职业匹配对照
     * @return 霍兰德职业兴趣测试结果与职业匹配对照集合
     */
    public List<HollandComparisontable> selectHollandComparisontableList(HollandComparisontable hollandComparisontable);

    /**
     * 新增霍兰德职业兴趣测试结果与职业匹配对照
     * 
     * @param hollandComparisontable 霍兰德职业兴趣测试结果与职业匹配对照
     * @return 结果
     */
    public int insertHollandComparisontable(HollandComparisontable hollandComparisontable);

    /**
     * 修改霍兰德职业兴趣测试结果与职业匹配对照
     * 
     * @param hollandComparisontable 霍兰德职业兴趣测试结果与职业匹配对照
     * @return 结果
     */
    public int updateHollandComparisontable(HollandComparisontable hollandComparisontable);

    /**
     * 批量删除霍兰德职业兴趣测试结果与职业匹配对照
     * 
     * @param comparisontableIds 需要删除的霍兰德职业兴趣测试结果与职业匹配对照主键集合
     * @return 结果
     */
    public int deleteHollandComparisontableByComparisontableIds(Long[] comparisontableIds);

    /**
     * 删除霍兰德职业兴趣测试结果与职业匹配对照信息
     * 
     * @param comparisontableId 霍兰德职业兴趣测试结果与职业匹配对照主键
     * @return 结果
     */
    public int deleteHollandComparisontableByComparisontableId(Long comparisontableId);
}
