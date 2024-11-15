package com.ruoyi.gk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 霍兰德职业兴趣测试人格对象 holland_personality
 * 
 * @author cyz
 * @date 2024-06-04
 */
public class HollandPersonality extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long personalityId;

    /** 人格倾向 */
    @Excel(name = "人格倾向")
    private String personalityInclined;

    /** 人格形态 */
    @Excel(name = "人格形态")
    private String personalityMorphology;

    /** 人格典型职业 */
    @Excel(name = "人格典型职业")
    private String personalityOccupation;

    public void setPersonalityId(Long personalityId) 
    {
        this.personalityId = personalityId;
    }

    public Long getPersonalityId() 
    {
        return personalityId;
    }
    public void setPersonalityInclined(String personalityInclined) 
    {
        this.personalityInclined = personalityInclined;
    }

    public String getPersonalityInclined() 
    {
        return personalityInclined;
    }
    public void setPersonalityMorphology(String personalityMorphology) 
    {
        this.personalityMorphology = personalityMorphology;
    }

    public String getPersonalityMorphology() 
    {
        return personalityMorphology;
    }
    public void setPersonalityOccupation(String personalityOccupation) 
    {
        this.personalityOccupation = personalityOccupation;
    }

    public String getPersonalityOccupation() 
    {
        return personalityOccupation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("personalityId", getPersonalityId())
            .append("personalityInclined", getPersonalityInclined())
            .append("personalityMorphology", getPersonalityMorphology())
            .append("personalityOccupation", getPersonalityOccupation())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
