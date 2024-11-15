package com.ruoyi.gk.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentPlanVo {
    /** 学校id */
    private Integer mcid;

    /** 专业id */
    @Excel(name = "专业id")
    private String specialId;

    /** 年份 */
    @Excel(name = "年份")
    private String year;
    /** 年份 */
    @Excel(name = "最低分")
    private String min;
    /** 年份 */
    @Excel(name = "最低位次")
    private Integer minSection;

    /** 年份 */
    @Excel(name = "名称")
    private String spname;
    /** 年份 */
    @Excel(name = "地区")
    private String localProvinceName;

    /** 招生数量 */
    @Excel(name = "招生数量")
    private Long num;

    /** 招生数量 */
    @Excel(name = "差距")
    private Integer gap;
    private String length;
    private String spInfo;
    private String tuition;

}
