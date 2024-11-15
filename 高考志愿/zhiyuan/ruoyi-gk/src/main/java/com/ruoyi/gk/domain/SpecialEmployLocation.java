package com.ruoyi.gk.domain;

import com.alibaba.fastjson2.JSONObject;
import lombok.*;
import com.ruoyi.common.annotation.Excel;

/**
 * 就业地区分布对象 special_employ_location
 * 
 * @author ruoyi
 * @date 2024-06-29
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialEmployLocation
{
    /** 就业分布数据 */
    @Excel(name = "就业分布数据")
    private JSONObject employmentAreasItem;

}
