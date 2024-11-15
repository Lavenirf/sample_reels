package com.ruoyi.gk.service.impl;

import java.util.*;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gk.domain.*;
import com.ruoyi.gk.domain.vo.HollandComparisontableVo;
import com.ruoyi.gk.mapper.HollandComparisontableMapper;
import com.ruoyi.gk.mapper.HollandUserComparisontableMapper;
import com.ruoyi.gk.mapper.UserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.mapper.HollandTopicMapper;
import com.ruoyi.gk.service.IHollandTopicService;

/**
 * 霍兰德职业兴趣测试题目Service业务层处理
 *
 * @author cyz
 * @date 2024-06-04
 */
@Service
public class HollandTopicServiceImpl implements IHollandTopicService
{
    @Autowired
    private HollandTopicMapper hollandTopicMapper;

    @Autowired
    private HollandComparisontableMapper hollandComparisontableMapper;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Autowired
    private HollandUserComparisontableMapper hollandUserComparisontableMapper;

    @Override
    public HollandComparisontableVo HollandTopicJudgment(List<TopicData> data) {
        hollandUserComparisontableMapper.deleteHollandUserComparisontableByuserId(SecurityUtils.getLoginUser().getUser().getUserId());
        int R = 0;
        int I = 0;
        int A = 0;
        int S = 0;
        int E = 0;
        int C = 0;

        List<HollandTopic> hollandTopics = hollandTopicMapper.selectHollandTopicList(null);
        for (TopicData topicData : data) {
            if (topicData != null) {
                //System.out.println(hollandTopics.get(topicData.getTopicId() - 4).toString());
                int outcome = topicData.getValue() > 0 ? 0 : 1;
                if (hollandTopics.get(topicData.getTopicId() - 4).getTopicAnswer() == outcome) {
                    switch (hollandTopics.get(topicData.getTopicId() - 4).getTopicType()) {
                        case "1": R += Math.abs(topicData.getValue()); break;
                        case "2": I += Math.abs(topicData.getValue()); break;
                        case "3": A += Math.abs(topicData.getValue()); break;
                        case "4": S += Math.abs(topicData.getValue()); break;
                        case "5": E += Math.abs(topicData.getValue()); break;
                        case "6": C += Math.abs(topicData.getValue()); break;
                    }
                }
            }
        }

        HollandComparisontableVo hollandComparisontableVo = new HollandComparisontableVo();
        hollandComparisontableVo.setR(R);
        hollandComparisontableVo.setI(I);
        hollandComparisontableVo.setA(A);
        hollandComparisontableVo.setS(S);
        hollandComparisontableVo.setE(E);
        hollandComparisontableVo.setC(C);

        // 使用字符串键创建并填充 Map
        Map<String, Integer> map = new HashMap<>();
        map.put("R", R);
        map.put("I", I);
        map.put("A", A);
        map.put("S", S);
        map.put("E", E);
        map.put("C", C);

        // 将 Map 条目存储在 List 中
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        // 按值进行降序排序
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));




        // 提取最高的三个键并合并为一个字符串
        StringBuilder topThreeKeys = new StringBuilder();
        for (int i = 0; i < 3 && i < list.size(); i++) {
            topThreeKeys.append(list.get(i).getKey());
        }

        String topThree = topThreeKeys.toString();
        System.out.println(topThree);
        HollandComparisontable hollandComparisontable = new HollandComparisontable();
        hollandComparisontable.setComparisontableMorphology(topThree);

        List<HollandComparisontable> hollandComparisontables = hollandComparisontableMapper.selectHollandComparisontableList(hollandComparisontable);

        hollandComparisontable.setComparisontableId(hollandComparisontables.get(0).getComparisontableId());
        hollandComparisontable.setComparisontableMorphology(hollandComparisontables.get(0).getComparisontableMorphology());
        hollandComparisontable.setTypicalProfession(hollandComparisontables.get(0).getTypicalProfession());

        /*UserInformation userInformation = new UserInformation();
        userInformation.setHollandResultId(String.valueOf(hollandComparisontable.getComparisontableId()));
        userDetailsMapper.updateUserDetails(userInformation);*/
        hollandComparisontableVo.setHollandComparisontable(hollandComparisontable);

        SysUser user = SecurityUtils.getLoginUser().getUser();
        HollandUserComparisontable hollandUserComparisontable = new HollandUserComparisontable();
        hollandUserComparisontable.setA((hollandComparisontableVo.getA()/30.0));
        hollandUserComparisontable.setS((hollandComparisontableVo.getS()/30.0));
        hollandUserComparisontable.setE((hollandComparisontableVo.getE()/30.0));
        hollandUserComparisontable.setC((hollandComparisontableVo.getC()/30.0));
        hollandUserComparisontable.setI((hollandComparisontableVo.getI()/30.0));
        hollandUserComparisontable.setR((hollandComparisontableVo.getR()/30.0));
        hollandUserComparisontable.setUserId(user.getUserId());
        hollandUserComparisontable.setHollandComparisontable(hollandComparisontable.getComparisontableId());
        hollandUserComparisontableMapper.insertHollandUserComparisontable(hollandUserComparisontable);
        // 获取自动生成的 ID
        int generatedId = hollandUserComparisontable.getId();

        UserInformation userDetails = new UserInformation();
        userDetails.setUserId(user.getUserId());
        userDetails.setHollandResultId(String.valueOf(generatedId));

        userDetailsMapper.updateUserDetails(userDetails);
        hollandComparisontableVo.setHollandResultId(generatedId);
        return hollandComparisontableVo;
    }

    /**
     * 查询霍兰德职业兴趣测试题目
     *
     * @param topicId 霍兰德职业兴趣测试题目主键
     * @return 霍兰德职业兴趣测试题目
     */
    @Override
    public HollandTopic selectHollandTopicByTopicId(Long topicId)
    {
        return hollandTopicMapper.selectHollandTopicByTopicId(topicId);
    }

    /**
     * 查询霍兰德职业兴趣测试题目列表
     *
     * @param hollandTopic 霍兰德职业兴趣测试题目
     * @return 霍兰德职业兴趣测试题目
     */
    @Override
    public List<HollandTopic> selectHollandTopicList(HollandTopic hollandTopic)
    {
        return hollandTopicMapper.selectHollandTopicList(hollandTopic);
    }

    /**
     * 新增霍兰德职业兴趣测试题目
     *
     * @param hollandTopic 霍兰德职业兴趣测试题目
     * @return 结果
     */
    @Override
    public int insertHollandTopic(HollandTopic hollandTopic)
    {
        hollandTopic.setCreateTime(DateUtils.getNowDate());
        return hollandTopicMapper.insertHollandTopic(hollandTopic);
    }

    /**
     * 修改霍兰德职业兴趣测试题目
     *
     * @param hollandTopic 霍兰德职业兴趣测试题目
     * @return 结果
     */
    @Override
    public int updateHollandTopic(HollandTopic hollandTopic)
    {
        hollandTopic.setUpdateTime(DateUtils.getNowDate());
        return hollandTopicMapper.updateHollandTopic(hollandTopic);
    }

    /**
     * 批量删除霍兰德职业兴趣测试题目
     *
     * @param topicIds 需要删除的霍兰德职业兴趣测试题目主键
     * @return 结果
     */
    @Override
    public int deleteHollandTopicByTopicIds(Long[] topicIds)
    {
        return hollandTopicMapper.deleteHollandTopicByTopicIds(topicIds);
    }

    /**
     * 删除霍兰德职业兴趣测试题目信息
     *
     * @param topicId 霍兰德职业兴趣测试题目主键
     * @return 结果
     */
    @Override
    public int deleteHollandTopicByTopicId(Long topicId)
    {
        return hollandTopicMapper.deleteHollandTopicByTopicId(topicId);
    }
}
