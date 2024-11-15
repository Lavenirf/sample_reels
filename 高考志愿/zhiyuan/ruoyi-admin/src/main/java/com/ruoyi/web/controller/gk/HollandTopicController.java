package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.gk.domain.TopicData;
import com.ruoyi.gk.domain.vo.HollandComparisontableVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.gk.domain.HollandTopic;
import com.ruoyi.gk.service.IHollandTopicService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 霍兰德职业兴趣测试题目Controller
 *
 * @author cyz
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/gk/topic")
public class HollandTopicController extends BaseController
{
    @Autowired
    private IHollandTopicService hollandTopicService;

    /**
     * 查询霍兰德职业兴趣测试结果
     */
    @PostMapping("/judgment")
    public RestBean<HollandComparisontableVo> Judgment(@RequestBody List<TopicData> data)
    {
        HollandComparisontableVo hollandComparisontable = hollandTopicService.HollandTopicJudgment(data);
        return RestBean.Success(hollandComparisontable);
    }

    /**
     * 查询霍兰德职业兴趣测试题目列表
     */
    @PreAuthorize("@ss.hasPermi('gk:topic:list')")
    @GetMapping("/list")
    public RestBean<List<HollandTopic>> list(HollandTopic hollandTopic)
    {
        startPage();
        List<HollandTopic> list = hollandTopicService.selectHollandTopicList(hollandTopic);
        return RestBean.Success(list);
    }

    /**
     * 导出霍兰德职业兴趣测试题目列表
     */
    @PreAuthorize("@ss.hasPermi('gk:topic:export')")
    @Log(title = "霍兰德职业兴趣测试题目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HollandTopic hollandTopic)
    {
        List<HollandTopic> list = hollandTopicService.selectHollandTopicList(hollandTopic);
        ExcelUtil<HollandTopic> util = new ExcelUtil<HollandTopic>(HollandTopic.class);
        util.exportExcel(response, list, "霍兰德职业兴趣测试题目数据");
    }

    /**
     * 获取霍兰德职业兴趣测试题目详细信息
     */
    @PreAuthorize("@ss.hasPermi('gk:topic:query')")
    @GetMapping(value = "/{topicId}")
    public RestBean<HollandTopic> getInfo(@PathVariable("topicId") Long topicId)
    {
        HollandTopic hollandTopic = hollandTopicService.selectHollandTopicByTopicId(topicId);
        return RestBean.Success(hollandTopic);
    }

    /**
     * 新增霍兰德职业兴趣测试题目
     */
    @PreAuthorize("@ss.hasPermi('gk:topic:add')")
    @Log(title = "霍兰德职业兴趣测试题目", businessType = BusinessType.INSERT)
    @PostMapping
    public RestBean<Integer> add(@RequestBody HollandTopic hollandTopic)
    {
        return RestBean.Success(hollandTopicService.insertHollandTopic(hollandTopic));
    }

    /**
     * 修改霍兰德职业兴趣测试题目
     */
    @PreAuthorize("@ss.hasPermi('gk:topic:edit')")
    @Log(title = "霍兰德职业兴趣测试题目", businessType = BusinessType.UPDATE)
    @PutMapping
    public RestBean<Integer> edit(@RequestBody HollandTopic hollandTopic)
    {
        return RestBean.Success(hollandTopicService.updateHollandTopic(hollandTopic));
    }

    /**
     * 删除霍兰德职业兴趣测试题目
     */
    @PreAuthorize("@ss.hasPermi('gk:topic:remove')")
    @Log(title = "霍兰德职业兴趣测试题目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{topicIds}")
    public RestBean<Integer> remove(@PathVariable Long[] topicIds)
    {
        return RestBean.Success(hollandTopicService.deleteHollandTopicByTopicIds(topicIds));
    }
}
