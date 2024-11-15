package com.ruoyi.gk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 霍兰德职业兴趣测试结果与职业匹配对照对象 holland_comparisontable
 *
 * @author cyz
 * @date 2024-06-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HollandComparisontable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long comparisontableId;

    /** 形态 */
    @Excel(name = "形态")
    private String comparisontableMorphology;
    /** 人格特征 */
    @Excel(name = "人格特征1")
    private String personality1;

        /** 人格特征 */
    @Excel(name = "人格特征2")
    private String personality2;

        /** 人格特征 */
    @Excel(name = "人格特征3")
    private String personality3;


    /** 典型职业 */
    @Excel(name = "典型职业")
    private String typicalProfession;

}
