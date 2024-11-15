package com.ruoyi.gk.service;


import com.ruoyi.gk.domain.SchoolSpecial;
import com.ruoyi.gk.domain.vo.SpecialDetailsVo;

import java.util.List;

/**
 * 专业详情Service接口
 * 
 * @author ruoyi
 * @date 2024-06-29
 */
public interface ISpecialAllDetailsService 
{
    /**
     * 查询专业详情
     * 
     * @param id 专业详情主键
     * @return 专业详情
     */
    public SpecialDetailsVo selectSpecialAllDetailsById(Long id);

    /**
     * 搜索
     */
    List<SchoolSpecial> selectSpecialByName(String name);
}
