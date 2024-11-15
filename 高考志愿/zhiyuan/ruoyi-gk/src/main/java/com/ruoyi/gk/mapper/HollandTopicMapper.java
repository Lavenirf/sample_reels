package com.ruoyi.gk.mapper;

import java.util.List;
import com.ruoyi.gk.domain.HollandTopic;

/**
 * 霍兰德职业兴趣测试题目Mapper接口
 *
 * @author cyz
 * @date 2024-06-04
 */
public interface HollandTopicMapper
{
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
     * 删除霍兰德职业兴趣测试题目
     *
     * @param topicId 霍兰德职业兴趣测试题目主键
     * @return 结果
     */
    public int deleteHollandTopicByTopicId(Long topicId);

    /**
     * 批量删除霍兰德职业兴趣测试题目
     *
     * @param topicIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHollandTopicByTopicIds(Long[] topicIds);
}
