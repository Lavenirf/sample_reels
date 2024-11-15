package com.ruoyi.gk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;

/**
 * 志愿详情对象 volunteer_form_details
 * 
 * @author ruoyi
 * @date 2024-07-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerFormDetails{
    /** id */
    private Long id;

    /** 专业id */
    @Excel(name = "专业id")
    private Long specialId;

    /** 学校id */
    @Excel(name = "学校id")
    private Long schoolId;
    /** 学校id */
    @Excel(name = "志愿表id")
    private Integer volunteerFromId;
    /** 段 */
    @Excel(name = "段")
    private Integer paragraph;

    /** 顺序 */
    @Excel(name = "顺序")
    private Integer order;
    /** 录取率 */
    @Excel(name = "录取率")
    private Integer glRatio;
    /** 专业分数线id */
    @Excel(name = "专业分数线id")
    private Integer mcid;

}
