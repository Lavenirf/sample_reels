package com.ruoyi.gk.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerFormDetailsVo {
    /**
     * id
     */
    private Long id;

    /**
     * 专业id
     */
    @Excel(name = "专业id")
    private Long specialId;

    /**
     * 学校id
     */
    @Excel(name = "学校id")
    private Long schoolId;
    /**
     * 学校id
     */
    @Excel(name = "志愿表id")
    private Long volunteerFromId;

    /**
     * 顺序
     */
    @Excel(name = "顺序")
    private Integer order;
    /**
     * 专业分数线id
     */
    @Excel(name = "专业分数线id")
    private Integer mcid;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 学校类型
     */
    private String typeName;
    /**
     * 公办
     */
    private String natureName;
    /**
     * 院校代码
     */
    private Integer codeEnroll;
    /**
     * 专业名称
     */
    private String specialName;
    /**
     * 专业代码
     */
    private String code;
    /**
     * 休学年限
     */
    private String limitYear;
    /**
     * 选科限制
     */
    private String selAdv;
    /**
     * belong
     */
    private String belong;
    /**
     * 双一流
     */
    private String dualClassName;
    /**
     * 211
     */
    private Integer f211;
    /**
     * 985
     */
    private Integer f985;
    /**
     * tagName
     */
    private String tagName;
    /** 段 */
    @Excel(name = "段")
    private Integer paragraph;
    /** 录取率 */
    @Excel(name = "录取率")
    private Integer glRatio;



}
