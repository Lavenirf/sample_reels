package com.ruoyi.gk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学校对象 schools
 * 
 * @author ruoyi
 * @date 2024-07-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schools extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;


    /** 主管部门 */
    @Excel(name = "主管部门")
    private String belong;

    /** 是否是中心城市 */
    @Excel(name = "是否是中心城市")
    private String central;

    /** 所属地区id */
    @Excel(name = "所属地区id")
    private Long cityId;

    /** 所属地区名称 */
    @Excel(name = "所属地区名称")
    private String cityName;

    /** 院校代码 */
    @Excel(name = "院校代码")
    private String codeEnroll;


    /** 地区代码 */
    @Excel(name = "地区代码")
    private Long countyId;

    /** 具体区域 */
    @Excel(name = "具体区域")
    private String countyName;


    /** 学校特色id */
    @Excel(name = "学校特色id")
    private String dualClass;

    /** 学校特色 */
    @Excel(name = "学校特色")
    private String dualClassName;

    /** 211 */
    @Excel(name = "211")
    private Long f211;

    /**985*/
    @Excel(name = "985")
    private Long f985;

    /** 是否招生 */
    @Excel(name = "是否招生")
    private Integer isRecruitment;

    /** 办学类型id */
    @Excel(name = "办学类型id")
    private String level;

    /** 办学类型 */
    @Excel(name = "办学类型")
    private String levelName;

    /** 学校名称 */
    @Excel(name = "学校名称")
    private String name;

    /** 办学性质id */
    @Excel(name = "办学性质id")
    private String nature;

    /** 办学性质 */
    @Excel(name = "办学性质")
    private String natureName;


    /** 省份id */
    @Excel(name = "省份id")
    private Long provinceId;

    /** 省份名称 */
    @Excel(name = "省份名称")
    private String provinceName;

    /** 推荐级别 */
    @Excel(name = "推荐级别")
    private Long recommendMasterLevel;

    /** 学校id */
    @Excel(name = "学校id")
    private Long schoolId;

    /** 学校类型 */
    @Excel(name = "学校类型")
    private String schoolType;

    /** 所属中央部署 */
    @Excel(name = "所属中央部署")
    private String tagName;

    /** 城镇地区 */
    @Excel(name = "城镇地区")
    private String townName;

    /** 院校类型id */
    @Excel(name = "院校类型id")
    private String type;

    /** 院校类型 */
    @Excel(name = "院校类型")
    private String typeName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long viewMonth;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String viewTotal;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long viewTotalNumber;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long viewWeek;

}
