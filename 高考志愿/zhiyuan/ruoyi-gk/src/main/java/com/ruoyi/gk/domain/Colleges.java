package com.ruoyi.gk.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 推荐院校对象 recommended_institutions
 *
 * @author ruoyi
 * @date 2024-07-18
 */
public class Colleges extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long shoolId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long number;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String scores;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal Recommendation;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal acceptanceRate;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setShoolId(Long shoolId)
    {
        this.shoolId = shoolId;
    }

    public Long getShoolId()
    {
        return shoolId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setNumber(Long number)
    {
        this.number = number;
    }

    public Long getNumber()
    {
        return number;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setScores(String scores)
    {
        this.scores = scores;
    }

    public String getScores()
    {
        return scores;
    }
    public void setRecommendation(BigDecimal Recommendation)
    {
        this.Recommendation = Recommendation;
    }

    public BigDecimal getRecommendation()
    {
        return Recommendation;
    }
    public void setAcceptanceRate(BigDecimal acceptanceRate)
    {
        this.acceptanceRate = acceptanceRate;
    }

    public BigDecimal getAcceptanceRate()
    {
        return acceptanceRate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shoolId", getShoolId())
            .append("name", getName())
            .append("number", getNumber())
            .append("userId", getUserId())
            .append("scores", getScores())
            .append("Recommendation", getRecommendation())
            .append("acceptanceRate", getAcceptanceRate())
            .toString();
    }

}
