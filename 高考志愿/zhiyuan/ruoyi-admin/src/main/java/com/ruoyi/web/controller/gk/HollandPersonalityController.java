package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.CodeState;
import com.ruoyi.common.core.domain.RestBean;
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
import com.ruoyi.gk.domain.HollandPersonality;
import com.ruoyi.gk.service.IHollandPersonalityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 霍兰德职业兴趣测试人格Controller
 *
 * @author cyz
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/gk/personality")
public class HollandPersonalityController extends BaseController
{
    @Autowired
    private IHollandPersonalityService hollandPersonalityService;

    /**
     * 查询霍兰德职业兴趣测试人格列表
     */
    @PreAuthorize("@ss.hasPermi('gk:personality:list')")
    @GetMapping("/list")
    public RestBean<List<HollandPersonality>> list(HollandPersonality hollandPersonality)
    {
        startPage();
        List<HollandPersonality> list = hollandPersonalityService.selectHollandPersonalityList(hollandPersonality);
        return RestBean.Success(list);
    }

    /**
     * 导出霍兰德职业兴趣测试人格列表
     */
    @PreAuthorize("@ss.hasPermi('gk:personality:export')")
    @Log(title = "霍兰德职业兴趣测试人格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HollandPersonality hollandPersonality)
    {
        List<HollandPersonality> list = hollandPersonalityService.selectHollandPersonalityList(hollandPersonality);
        ExcelUtil<HollandPersonality> util = new ExcelUtil<HollandPersonality>(HollandPersonality.class);
        util.exportExcel(response, list, "霍兰德职业兴趣测试人格数据");
    }

    /**
     * 获取霍兰德职业兴趣测试人格详细信息
     */
    @PreAuthorize("@ss.hasPermi('gk:personality:query')")
    @GetMapping(value = "/{personalityId}")
    public RestBean<HollandPersonality> getInfo(@PathVariable("personalityId") Long personalityId)
    {
        HollandPersonality hollandPersonality = hollandPersonalityService.selectHollandPersonalityByPersonalityId(personalityId);
        return RestBean.Success(hollandPersonality);
    }

    /**
     * 新增霍兰德职业兴趣测试人格
     */
    @PreAuthorize("@ss.hasPermi('gk:personality:add')")
    @Log(title = "霍兰德职业兴趣测试人格", businessType = BusinessType.INSERT)
    @PostMapping
    public RestBean<Integer> add(@RequestBody HollandPersonality hollandPersonality)
    {
        return RestBean.Success(hollandPersonalityService.insertHollandPersonality(hollandPersonality));
    }

    /**
     * 修改霍兰德职业兴趣测试人格
     */
    @PreAuthorize("@ss.hasPermi('gk:personality:edit')")
    @Log(title = "霍兰德职业兴趣测试人格", businessType = BusinessType.UPDATE)
    @PutMapping
    public RestBean<Integer> edit(@RequestBody HollandPersonality hollandPersonality)
    {
        return RestBean.Success(hollandPersonalityService.updateHollandPersonality(hollandPersonality));
    }

    /**
     * 删除霍兰德职业兴趣测试人格
     */
    @PreAuthorize("@ss.hasPermi('gk:personality:remove')")
    @Log(title = "霍兰德职业兴趣测试人格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{personalityIds}")
    public RestBean<Integer> remove(@PathVariable Long[] personalityIds)
    {
        return RestBean.Success(hollandPersonalityService.deleteHollandPersonalityByPersonalityIds(personalityIds));
    }
}
