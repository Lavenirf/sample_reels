package com.ruoyi.gk.mapper;

import com.ruoyi.gk.domain.SchoolSpecial;
import com.ruoyi.gk.domain.SpecialDetails;

import java.util.List;

/**
 * 专业详情Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-29
 */
public interface SpecialAllDetailsMapper 
{
    /**
     * 查询专业详情
     * 
     * @param id 专业详情主键
     * @return 专业详情
     */
    public SpecialDetails selectSpecialAllDetailsById(Long id);

    List<SchoolSpecial> selectSpecialByName(String name);
}
