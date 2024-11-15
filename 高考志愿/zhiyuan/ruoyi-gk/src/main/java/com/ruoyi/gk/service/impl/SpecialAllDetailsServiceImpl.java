package com.ruoyi.gk.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.gk.domain.*;
import com.ruoyi.gk.domain.vo.SpecialDetailsVo;
import com.ruoyi.gk.mapper.SpecialAllDetailsMapper;
import com.ruoyi.gk.service.ISpecialAllDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 专业详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-29
 */
@Service
public class SpecialAllDetailsServiceImpl implements ISpecialAllDetailsService
{

    @Autowired
    private SpecialAllDetailsMapper specialAllDetailsMapper;

    /**
     * 查询专业详情
     * 
     * @param id 专业详情主键
     * @return 专业详情
     */
    @Override
    public SpecialDetailsVo selectSpecialAllDetailsById(Long id)
    {
        // 查询专业详情
        SpecialDetails specialDetails = specialAllDetailsMapper.selectSpecialAllDetailsById(id);
        // 创建专业详情视图对象
        SpecialDetailsVo specialDetailsVo = new SpecialDetailsVo();

        // 映射就业领域列表
        List<JSONObject> employmentAreasList = specialDetails.getEmploymentAreas().stream()
                .map(SpecialEmployLocation::getEmploymentAreasItem)
                .collect(Collectors.toList());

        // 映射就业岗位列表
        List<JSONObject> employPositionsList = specialDetails.getEmploymentPositions().stream()
                .map(SpecialEmployPositions::getEmploymentPositionsItem)
                .collect(Collectors.toList());

        // 映射就业分布列表
        List<JSONObject> employmentDistributionList = specialDetails.getEmploymentDistribution().stream()
                .map(SpecialJobDetails::getEmploymentDistributionItem)
                .collect(Collectors.toList());

        // 复制其他属性
        BeanUtil.copyProperties(specialDetails, specialDetailsVo);

        // 设置映射后的列表
        specialDetailsVo.setAreas(employmentAreasList);
        specialDetailsVo.setPositions(employPositionsList);
        specialDetailsVo.setDistribution(employmentDistributionList);

        return specialDetailsVo;
    }

    @Override
    public List<SchoolSpecial> selectSpecialByName(String name) {
        return specialAllDetailsMapper.selectSpecialByName(name);
    }
}
