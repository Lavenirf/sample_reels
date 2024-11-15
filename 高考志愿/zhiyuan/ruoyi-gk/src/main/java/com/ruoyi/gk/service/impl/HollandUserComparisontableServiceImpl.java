package com.ruoyi.gk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.gk.domain.vo.HollandUserComparisontableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.mapper.HollandUserComparisontableMapper;
import com.ruoyi.gk.domain.HollandUserComparisontable;
import com.ruoyi.gk.service.IHollandUserComparisontableService;

/**
 * 霍兰德测试用户结果Service业务层处理
 * 
 * @author cyz
 * @date 2024-07-28
 */
@Service
public class HollandUserComparisontableServiceImpl implements IHollandUserComparisontableService 
{
    @Autowired
    private HollandUserComparisontableMapper hollandUserComparisontableMapper;

    /**
     * 查询霍兰德测试用户结果
     * 
     * @param id 霍兰德测试用户结果主键
     * @return 霍兰德测试用户结果
     */
    @Override
    public HollandUserComparisontableVo selectHollandUserComparisontableById(Long id)
    {
        return hollandUserComparisontableMapper.selectHollandUserComparisontableById(id);
    }

    /**
     * 查询霍兰德测试用户结果列表
     * 
     * @param hollandUserComparisontable 霍兰德测试用户结果
     * @return 霍兰德测试用户结果
     */
    @Override
    public List<HollandUserComparisontable> selectHollandUserComparisontableList(HollandUserComparisontable hollandUserComparisontable)
    {
        return hollandUserComparisontableMapper.selectHollandUserComparisontableList(hollandUserComparisontable);
    }

    /**
     * 新增霍兰德测试用户结果
     * 
     * @param hollandUserComparisontable 霍兰德测试用户结果
     * @return 结果
     */
    @Override
    public int insertHollandUserComparisontable(HollandUserComparisontable hollandUserComparisontable)
    {
        hollandUserComparisontable.setCreateTime(DateUtils.getNowDate());
        return hollandUserComparisontableMapper.insertHollandUserComparisontable(hollandUserComparisontable);
    }

    /**
     * 修改霍兰德测试用户结果
     * 
     * @param hollandUserComparisontable 霍兰德测试用户结果
     * @return 结果
     */
    @Override
    public int updateHollandUserComparisontable(HollandUserComparisontable hollandUserComparisontable)
    {
        hollandUserComparisontable.setUpdateTime(DateUtils.getNowDate());
        return hollandUserComparisontableMapper.updateHollandUserComparisontable(hollandUserComparisontable);
    }

    /**
     * 批量删除霍兰德测试用户结果
     * 
     * @param ids 需要删除的霍兰德测试用户结果主键
     * @return 结果
     */
    @Override
    public int deleteHollandUserComparisontableByIds(Long[] ids)
    {
        return hollandUserComparisontableMapper.deleteHollandUserComparisontableByIds(ids);
    }

    /**
     * 删除霍兰德测试用户结果信息
     * 
     * @param id 霍兰德测试用户结果主键
     * @return 结果
     */
    @Override
    public int deleteHollandUserComparisontableById(Long id)
    {
        return hollandUserComparisontableMapper.deleteHollandUserComparisontableById(id);
    }
}
