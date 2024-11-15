package com.ruoyi.gk.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * enrollmentPlan对象 enrollment_plan
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentPlan {

    /** 学校id */
    private String schoolId;
    /** 学校id */
    private Integer id;

    /** 专业id */
    @Excel(name = "专业id")
    private String specialId;

    /** 年份 */
    @Excel(name = "年份")
    private String year;

    /** 地区 */
    @Excel(name = "地区")
    private String area;

    /** 招生数量 */
    @Excel(name = "招生数量")
    private Long num;

    /** 休学年限 */
    @Excel(name = "休学年限")
    private String length;

    /** 学费 */
    @Excel(name = "学费")
    private String tuition;

    /** 专本科 */
    @Excel(name = "专本科")
    private String level1Name;

    /** 学科 */
    @Excel(name = "学科")
    private String level2Name;

    /** 大类 */
    @Excel(name = "大类")
    private String level3Name;

    /** 专业代码 */
    @Excel(name = "专业代码")
    private String spcode;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String spname;    /** 专业名称 */
    @Excel(name = "专业限选")
    private String spInfo;

    /** 段数 */
    @Excel(name = "段数")
    private String localBatchName;

    /** 办学类型 */
    @Excel(name = "办学类型")
    private String zslxName;
}
