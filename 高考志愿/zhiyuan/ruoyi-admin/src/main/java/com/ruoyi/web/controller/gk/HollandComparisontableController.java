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
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.gk.domain.HollandComparisontable;
import com.ruoyi.gk.service.IHollandComparisontableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 霍兰德职业兴趣测试结果与职业匹配对照Controller
 *
 * @author cyz
 * @date 2024-06-05
 */
@RestController
@RequestMapping("/gk/comparisontable")
public class HollandComparisontableController extends BaseController
{
    @Autowired
    private IHollandComparisontableService hollandComparisontableService;

    /**
     * 查询霍兰德职业兴趣测试结果与职业匹配对照列表
     */
    @PreAuthorize("@ss.hasPermi('gk:comparisontable:list')")
    @GetMapping("/list")
    public RestBean<List<HollandComparisontable>> list(HollandComparisontable hollandComparisontable)
    {
        startPage();
        List<HollandComparisontable> list = hollandComparisontableService.selectHollandComparisontableList(hollandComparisontable);
        return RestBean.Success(list);
    }

    /**
     * 导出霍兰德职业兴趣测试结果与职业匹配对照列表
     */
    @PreAuthorize("@ss.hasPermi('gk:comparisontable:export')")
    @Log(title = "霍兰德职业兴趣测试结果与职业匹配对照", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HollandComparisontable hollandComparisontable)
    {
        List<HollandComparisontable> list = hollandComparisontableService.selectHollandComparisontableList(hollandComparisontable);
        ExcelUtil<HollandComparisontable> util = new ExcelUtil<HollandComparisontable>(HollandComparisontable.class);
        util.exportExcel(response, list, "霍兰德职业兴趣测试结果与职业匹配对照数据");
    }

    /**
     * 获取霍兰德职业兴趣测试结果与职业匹配对照详细信息
     */
    @PreAuthorize("@ss.hasPermi('gk:comparisontable:query')")
    @GetMapping(value = "/{comparisontableId}")
    public RestBean<HollandComparisontable> getInfo(@PathVariable("comparisontableId") Long comparisontableId)
    {
        HollandComparisontable hollandComparisontable = hollandComparisontableService.selectHollandComparisontableByComparisontableId(comparisontableId);
        return RestBean.Success(hollandComparisontable);
    }

    /**
     * 新增霍兰德职业兴趣测试结果与职业匹配对照
     */
    @PreAuthorize("@ss.hasPermi('gk:comparisontable:add')")
    @Log(title = "霍兰德职业兴趣测试结果与职业匹配对照", businessType = BusinessType.INSERT)
    @PostMapping
    public RestBean<Integer> add(@RequestBody HollandComparisontable hollandComparisontable)
    {
        return RestBean.Success(hollandComparisontableService.insertHollandComparisontable(hollandComparisontable));
    }

    /**
     * 修改霍兰德职业兴趣测试结果与职业匹配对照
     */
    @PreAuthorize("@ss.hasPermi('gk:comparisontable:edit')")
    @Log(title = "霍兰德职业兴趣测试结果与职业匹配对照", businessType = BusinessType.UPDATE)
    @PutMapping
    public RestBean<Integer> edit(@RequestBody HollandComparisontable hollandComparisontable)
    {
        return RestBean.Success(hollandComparisontableService.updateHollandComparisontable(hollandComparisontable));
    }

    /**
     * 删除霍兰德职业兴趣测试结果与职业匹配对照
     */
    @PreAuthorize("@ss.hasPermi('gk:comparisontable:remove')")
    @Log(title = "霍兰德职业兴趣测试结果与职业匹配对照", businessType = BusinessType.DELETE)
	@DeleteMapping("/{comparisontableIds}")
    public RestBean<Integer> remove(@PathVariable Long[] comparisontableIds)
    {
        return RestBean.Success(hollandComparisontableService.deleteHollandComparisontableByComparisontableIds(comparisontableIds));
    }
}
