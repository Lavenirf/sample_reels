package com.ruoyi.gk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学校录取分数线对象 school_scores
 *
 * @author cyz
 * @date 2024-07-16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolScores extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    @Excel(name = "")
    private String answerurl;

    /**  */
    @Excel(name = "")
    private String average;

    /**  */
    @Excel(name = "")
    private String avgSection;

    /**  */
    @Excel(name = "")
    private String cityName;

    /**  */
    @Excel(name = "")
    private String countyName;

    /**  */
    @Excel(name = "")
    private Long dualClass;

    /**  */
    @Excel(name = "")
    private String dualClassName;

    /**  */
    @Excel(name = "")
    private Long filing;

    /**  */
    @Excel(name = "")
    private Long firstKm;

    /**  */
    @Excel(name = "")
    private String localBatchId;

    /**  */
    @Excel(name = "")
    private String localBatchName;

    /**  */
    @Excel(name = "")
    private String localProvinceName;

    /**  */
    @Excel(name = "")
    private String localTypeName;
    /**  */
    @Excel(name = "")
    private String max;

    /**  */
    @Excel(name = "")
    private Long min;

    /**  */
    @Excel(name = "")
    private Long minSection;

    /**  */
    @Excel(name = "")
    private String name;

    /**  */
    @Excel(name = "")
    private String natureName;

    /**  */
    @Excel(name = "")
    private String num;

    /**  */
    @Excel(name = "")
    private String proscore;

    /**  */
    @Excel(name = "")
    private Long provinceId;

    /**  */
    @Excel(name = "")
    private String provinceName;

    /**  */
    @Excel(name = "")
    private Long schoolId;

    /**  */
    @Excel(name = "")
    private Long sgFxk;

    /**  */
    @Excel(name = "")
    private String sgInfo;

    /**  */
    @Excel(name = "")
    private String sgName;

    /**  */
    @Excel(name = "")
    private String sgSxk;

    /**  */
    @Excel(name = "")
    private Long sgType;

    /**  */
    @Excel(name = "")
    private String specialGroup;

    /**  */
    @Excel(name = "")
    private String xclevelName;

    /**  */
    @Excel(name = "")
    private Long year;

    /**  */
    @Excel(name = "")
    private String zslxName;
}
