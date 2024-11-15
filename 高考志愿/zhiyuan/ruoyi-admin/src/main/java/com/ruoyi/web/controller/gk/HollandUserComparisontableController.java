package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.gk.domain.vo.HollandUserComparisontableVo;
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
import com.ruoyi.gk.domain.HollandUserComparisontable;
import com.ruoyi.gk.service.IHollandUserComparisontableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 霍兰德测试用户结果Controller
 *
 * @author cyz
 * @date 2024-07-28
 */
@RestController
@RequestMapping("/gk/usercomparisontable")
public class HollandUserComparisontableController extends BaseController
{
    @Autowired
    private IHollandUserComparisontableService hollandUserComparisontableService;

    /**
     * 查询霍兰德测试用户结果列表
     */
    @PreAuthorize("@ss.hasPermi('gk:usercomparisontable:list')")
    @GetMapping("/list")
    public TableDataInfo list(HollandUserComparisontable hollandUserComparisontable)
    {
        startPage();
        List<HollandUserComparisontable> list = hollandUserComparisontableService.selectHollandUserComparisontableList(hollandUserComparisontable);
        return getDataTable(list);
    }

    /**
     * 导出霍兰德测试用户结果列表
     */
    @PreAuthorize("@ss.hasPermi('gk:usercomparisontable:export')")
    @Log(title = "霍兰德测试用户结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HollandUserComparisontable hollandUserComparisontable)
    {
        List<HollandUserComparisontable> list = hollandUserComparisontableService.selectHollandUserComparisontableList(hollandUserComparisontable);
        ExcelUtil<HollandUserComparisontable> util = new ExcelUtil<HollandUserComparisontable>(HollandUserComparisontable.class);
        util.exportExcel(response, list, "霍兰德测试用户结果数据");
    }

    /**
     * 获取霍兰德测试用户结果详细信息
     */
    @PreAuthorize("@ss.hasPermi('gk:usercomparisontable:query')")
    @GetMapping(value = "/{id}")
    public RestBean<HollandUserComparisontableVo> getInfo(@PathVariable("id") Long id)
    {
        return RestBean.Success(hollandUserComparisontableService.selectHollandUserComparisontableById(id));
    }

    /**
     * 新增霍兰德测试用户结果
     */
    @PreAuthorize("@ss.hasPermi('gk:usercomparisontable:add')")
    @Log(title = "霍兰德测试用户结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HollandUserComparisontable hollandUserComparisontable)
    {
        return toAjax(hollandUserComparisontableService.insertHollandUserComparisontable(hollandUserComparisontable));
    }

    /**
     * 修改霍兰德测试用户结果
     */
    @PreAuthorize("@ss.hasPermi('gk:usercomparisontable:edit')")
    @Log(title = "霍兰德测试用户结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HollandUserComparisontable hollandUserComparisontable)
    {
        return toAjax(hollandUserComparisontableService.updateHollandUserComparisontable(hollandUserComparisontable));
    }

    /**
     * 删除霍兰德测试用户结果
     */
    @PreAuthorize("@ss.hasPermi('gk:usercomparisontable:remove')")
    @Log(title = "霍兰德测试用户结果", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hollandUserComparisontableService.deleteHollandUserComparisontableByIds(ids));
    }
}
