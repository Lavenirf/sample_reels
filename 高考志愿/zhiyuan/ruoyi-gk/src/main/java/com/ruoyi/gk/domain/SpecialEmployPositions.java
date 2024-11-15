package com.ruoyi.gk.domain;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;

/**
 * 就业岗位分布对象 special_employ_positions
 * 
 * @author ruoyi
 * @date 2024-06-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialEmployPositions
{
    /** 就业岗位分布数据 */
    @Excel(name = "就业岗位分布数据")
    private JSONObject employmentPositionsItem;

}
