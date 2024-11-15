package com.ruoyi.gk.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 霍兰德测试用户结果对象 holland_user_comparisontable
 *
 * @author cyz
 * @date 2024-07-28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HollandUserComparisontable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private int id;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private double r;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private double i;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private double a;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private double c;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private double e;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private double s;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long hollandComparisontable;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    @Override
    public String toString() {
        return "HollandUserComparisontable{" +
                "id=" + id +
                ", r=" + r +
                ", i=" + i +
                ", a=" + a +
                ", c=" + c +
                ", e=" + e +
                ", s=" + s +
                ", hollandComparisontable=" + hollandComparisontable +
                ", userId=" + userId +
                '}';
    }
}