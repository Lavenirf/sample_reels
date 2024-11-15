package com.ruoyi.gk.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialReduceVo {
    private Long id;
    private Long specialId;
    /**
     * 专业名称
     */

    private String specialName;

    /**
     * 专业类别(本科、专科)
     */

    private String level1Name;

    /**
     * 专业类型
     */

    private String type;

    /**
     * 所属大类
     */

    private String typeDetail;

    /**
     * 专业代码
     */

    private String code;

    /**
     * 修业年限
     */

    private String limitYear;

    /**
     * 授予学位
     */

    private String degree;

    /**
     * 平均年薪
     */

    private Long avgSalary;


    private String sexRate;

    /**
     * 文理比例
     */
    private String artScienceRate;

}
