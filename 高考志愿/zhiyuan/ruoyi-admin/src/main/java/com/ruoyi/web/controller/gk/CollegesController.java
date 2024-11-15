package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.gk.domain.Colleges;
import com.ruoyi.gk.service.ICollegesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 推荐院校Controller
 *
 * @author ruoyi
 * @date 2024-07-18
 */
@RestController
@RequestMapping("/gk/Colleges")
public class CollegesController extends BaseController
{
    @Autowired
    private ICollegesService collegesService;

    /**
     * 查询推荐院校列表
     */
    @PreAuthorize("@ss.hasPermi('gk:Colleges:list')")
    @GetMapping("/list")
    public TableDataInfo list(Colleges colleges)
    {
        startPage();
        List<Colleges> list = collegesService.selectCollegesList(colleges);
        return getDataTable(list);
    }

    /**
     * 导出推荐院校列表
     */
    @PreAuthorize("@ss.hasPermi('gk:Colleges:export')")
    @Log(title = "推荐院校", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Colleges colleges)
    {
        List<Colleges> list = collegesService.selectCollegesList(colleges);
        ExcelUtil<Colleges> util = new ExcelUtil<Colleges>(Colleges.class);
        util.exportExcel(response, list, "推荐院校数据");
    }

    /**
     * 获取推荐院校详细信息
     */
    @PreAuthorize("@ss.hasPermi('gk:Colleges:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(collegesService.selectCollegesById(id));
    }

    /**
     * 新增推荐院校
     */
    @PreAuthorize("@ss.hasPermi('gk:Colleges:add')")
    @Log(title = "推荐院校", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Colleges colleges)
    {
        return toAjax(collegesService.insertColleges(colleges));
    }

    /**
     * 修改推荐院校
     */
    @PreAuthorize("@ss.hasPermi('gk:Colleges:edit')")
    @Log(title = "推荐院校", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Colleges colleges)
    {
        return toAjax(collegesService.updateColleges(colleges));
    }

    /**
     * 删除推荐院校
     */
    @PreAuthorize("@ss.hasPermi('gk:Colleges:remove')")
    @Log(title = "推荐院校", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(collegesService.deleteCollegesByIds(ids));
    }
}
