package com.ruoyi.gk.service;

import java.util.List;
import com.ruoyi.gk.domain.HollandUserComparisontable;
import com.ruoyi.gk.domain.vo.HollandUserComparisontableVo;

/**
 * 霍兰德测试用户结果Service接口
 * 
 * @author cyz
 * @date 2024-07-28
 */
public interface IHollandUserComparisontableService 
{
    /**
     * 查询霍兰德测试用户结果
     * 
     * @param id 霍兰德测试用户结果主键
     * @return 霍兰德测试用户结果
     */
    public HollandUserComparisontableVo selectHollandUserComparisontableById(Long id);

    /**
     * 查询霍兰德测试用户结果列表
     * 
     * @param hollandUserComparisontable 霍兰德测试用户结果
     * @return 霍兰德测试用户结果集合
     */
    public List<HollandUserComparisontable> selectHollandUserComparisontableList(HollandUserComparisontable hollandUserComparisontable);

    /**
     * 新增霍兰德测试用户结果
     * 
     * @param hollandUserComparisontable 霍兰德测试用户结果
     * @return 结果
     */
    public int insertHollandUserComparisontable(HollandUserComparisontable hollandUserComparisontable);

    /**
     * 修改霍兰德测试用户结果
     * 
     * @param hollandUserComparisontable 霍兰德测试用户结果
     * @return 结果
     */
    public int updateHollandUserComparisontable(HollandUserComparisontable hollandUserComparisontable);

    /**
     * 批量删除霍兰德测试用户结果
     * 
     * @param ids 需要删除的霍兰德测试用户结果主键集合
     * @return 结果
     */
    public int deleteHollandUserComparisontableByIds(Long[] ids);

    /**
     * 删除霍兰德测试用户结果信息
     * 
     * @param id 霍兰德测试用户结果主键
     * @return 结果
     */
    public int deleteHollandUserComparisontableById(Long id);
}
