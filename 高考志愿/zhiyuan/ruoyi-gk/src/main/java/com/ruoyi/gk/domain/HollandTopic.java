package com.ruoyi.gk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 霍兰德职业兴趣测试题目对象 holland_topic
 *
 * @author cyz
 * @date 2024-06-04
 */
public class HollandTopic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 题目ID */
    private Long topicId;

    /** 题目内容 */
    @Excel(name = "题目内容")
    private String topicContent;

    /** 题目形态 */
    @Excel(name = "题目形态")
    private String topicType;

    /** 题目答案 */
    @Excel(name = "题目答案")
    private Integer topicAnswer;

    public void setTopicId(Long topicId)
    {
        this.topicId = topicId;
    }

    public Long getTopicId()
    {
        return topicId;
    }
    public void setTopicContent(String topicContent)
    {
        this.topicContent = topicContent;
    }

    public String getTopicContent()
    {
        return topicContent;
    }
    public void setTopicType(String topicType)
    {
        this.topicType = topicType;
    }

    public String getTopicType()
    {
        return topicType;
    }
    public void setTopicAnswer(Integer topicAnswer)
    {
        this.topicAnswer = topicAnswer;
    }

    public Integer getTopicAnswer()
    {
        return topicAnswer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("topicId", getTopicId())
                .append("topicContent", getTopicContent())
                .append("topicType", getTopicType())
                .append("topicAnswer", getTopicAnswer())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
