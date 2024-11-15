package com.ruoyi.gk.domain;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;

/**
 * 就业行业分布对象 special_job_details
 * 
 * @author ruoyi
 * @date 2024-06-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialJobDetails
{
    /** 就业行业分布json数据 */
    @Excel(name = "就业行业分布json数据")
    private JSONObject employmentDistributionItem;
}
