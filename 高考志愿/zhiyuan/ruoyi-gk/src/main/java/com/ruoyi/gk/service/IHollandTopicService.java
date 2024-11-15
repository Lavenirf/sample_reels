package com.ruoyi.gk.service;

import java.util.List;
import com.ruoyi.gk.domain.HollandTopic;
import com.ruoyi.gk.domain.TopicData;
import com.ruoyi.gk.domain.HollandComparisontable;
import com.ruoyi.gk.domain.vo.HollandComparisontableVo;

/**
 * 霍兰德职业兴趣测试题目Service接口
 *
 * @author cyz
 * @date 2024-06-04
 */
public interface IHollandTopicService
{
    /**
     * 查询霍兰德职业兴趣测试结果
     *
     * @param data 霍兰德职业兴趣测试题目答案列表
     * @return 霍兰德职业兴趣测试结构
     */
    public HollandComparisontableVo HollandTopicJudgment(List<TopicData> data);
    /**
     * 查询霍兰德职业兴趣测试题目
     *
     * @param topicId 霍兰德职业兴趣测试题目主键
     * @return 霍兰德职业兴趣测试题目
     */
    public HollandTopic selectHollandTopicByTopicId(Long topicId);

    /**
     * 查询霍兰德职业兴趣测试题目列表
     *
     * @param hollandTopic 霍兰德职业兴趣测试题目
     * @return 霍兰德职业兴趣测试题目集合
     */
    public List<HollandTopic> selectHollandTopicList(HollandTopic hollandTopic);

    /**
     * 新增霍兰德职业兴趣测试题目
     *
     * @param hollandTopic 霍兰德职业兴趣测试题目
     * @return 结果
     */
    public int insertHollandTopic(HollandTopic hollandTopic);

    /**
     * 修改霍兰德职业兴趣测试题目
     *
     * @param hollandTopic 霍兰德职业兴趣测试题目
     * @return 结果
     */
    public int updateHollandTopic(HollandTopic hollandTopic);

    /**
     * 批量删除霍兰德职业兴趣测试题目
     *
     * @param topicIds 需要删除的霍兰德职业兴趣测试题目主键集合
     * @return 结果
     */
    public int deleteHollandTopicByTopicIds(Long[] topicIds);

    /**
     * 删除霍兰德职业兴趣测试题目信息
     *
     * @param topicId 霍兰德职业兴趣测试题目主键
     * @return 结果
     */
    public int deleteHollandTopicByTopicId(Long topicId);
}
