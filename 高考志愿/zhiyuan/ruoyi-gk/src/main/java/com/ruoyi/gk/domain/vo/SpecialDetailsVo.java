package com.ruoyi.gk.domain.vo;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 专业详情对象 special_all_details
 *
 * @author wbl
 * @date 2024-06-29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialDetailsVo {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 专业id
     */
    @Excel(name = "专业id")
    private Long specialId;

    /**
     * 专业名称
     */
    @Excel(name = "专业名称")
    private String specialName;

    /**
     * 专业类别(本科、专科)
     */
    @Excel(name = "专业类别(本科、专科)")
    private String level1Name;

    /**
     * 专业类型
     */
    @Excel(name = "专业类型")
    private String type;

    /**
     * 所属大类
     */
    @Excel(name = "所属大类")
    private String typeDetail;

    /**
     * 专业代码
     */
    @Excel(name = "专业代码")
    private String code;

    /**
     * 修业年限
     */
    @Excel(name = "修业年限")
    private String limitYear;

    /**
     * 授予学位
     */
    @Excel(name = "授予学位")
    private String degree;

    /**
     * 平均年薪
     */
    @Excel(name = "平均年薪")
    private Long avgSalary;

    /**
     * 选课建议
     */
    @Excel(name = "选课建议")
    private String selAdv;

    /**
     * 考研方向
     */
    @Excel(name = "考研方向")
    private String direction;

    /**
     * 社会名人
     */
    @Excel(name = "社会名人")
    private String celebrity;

    /**
     * 开设课程
     */
    @Excel(name = "开设课程")
    private String course;

    /**
     * 专业就业
     */
    @Excel(name = "专业就业")
    private String job;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String level3;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String level2;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String level1;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String skill;

    /**
     * 专业简介(专业介绍)
     */
    @Excel(name = "专业简介(专业介绍)")
    private String isWhat;

    /**
     * 专业简介(开设课程)
     */
    @Excel(name = "专业简介(开设课程)")
    private String learnWhat;

    /**
     * 专业简介(未来就业)
     */
    @Excel(name = "专业简介(未来就业)")
    private String doWhat;

    /**
     * 2021年就业率
     */
    @Excel(name = "2021年就业率")
    private String work21;

    /**
     * 2022年就业率
     */
    @Excel(name = "2022年就业率")
    private String work22;

    /**
     * 2023年就业率
     */
    @Excel(name = "2023年就业率")
    private String work23;

    /**
     * 性别比例
     */
    @Excel(name = "性别比例")
    private String sexRate;

    /**
     * 文理比例
     */
    @Excel(name = "文理比例")
    private String artScienceRate;

    /**
     * 专业详解
     */
    @Excel(name = "专业详解")
    private String content;

    /**
     * 毕业五年月星
     */
    @Excel(name = "毕业五年月星")
    private Long graduateFiveSalary;

    /**
     * 最多就业岗位
     */
    @Excel(name = "最多就业岗位")
    private String post;

    /**
     * 最多就业行业
     */
    @Excel(name = "最多就业行业")
    private String mentarea;

    /**
     * 最多就业地区
     */
    @Excel(name = "最多就业地区")
    private String industry;

    /** 就业分布数据 */
    @Excel(name = "就业分布数据")
    private List<JSONObject> areas;

    /** 就业岗位分布数据 */
    @Excel(name = "就业岗位分布数据")
    private List<JSONObject> positions;

    /** 本专业薪酬数组 */
    @Excel(name = "本专业薪酬数组")
    private String thisFreshGraduates;

    /** 所有专业薪酬数组 */
    @Excel(name = "所有专业薪酬数组")
    private String outFreshGraduates;
    /** 就业行业分布json数据 */
    @Excel(name = "就业行业分布json数据")
    private List<JSONObject> distribution;
}
