package com.ruoyi.gk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学校专业对象 school_special
 * 
 * @author ruoyi
 * @date 2024-06-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolSpecial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "special_id")
    private Long specialId;

    /** 男比例 */
    @Excel(name = "男比例")
    private String boyRate;

    /** 授予学位 */
    @Excel(name = "授予学位")
    private String degree;

    /** 平均薪酬 */
    @Excel(name = "平均薪酬")
    private Long fivesalaryavg;

    /** 女比例 */
    @Excel(name = "女比例")
    private String girlRate;

    /** 专业层次id */
    @Excel(name = "专业层次id")
    private Long level1;

    /** 专业层次 */
    @Excel(name = "专业层次")
    private String level1Name;

    /** 专业门类id */
    @Excel(name = "专业门类id")
    private Long level2;

    /** 专业门类 */
    @Excel(name = "专业门类")
    private String level2Name;

    /** 专业大类id */
    @Excel(name = "专业大类id")
    private Long level3;

    /** 专业大类 */
    @Excel(name = "专业大类")
    private String level3Name;

    /** 学制 */
    @Excel(name = "学制")
    private String limitYear;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String name;

    /** 专业代码 */
    @Excel(name = "专业代码")
    private String spcode;

}
