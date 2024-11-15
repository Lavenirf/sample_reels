package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.gk.domain.vo.SchoolDetailsVo;
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
import com.ruoyi.gk.domain.SchoolDetails;
import com.ruoyi.gk.service.ISchoolDetailsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学校详情Controller
 * 
 * @author ruoyi
 * @date 2024-08-03
 */
@RestController
@RequestMapping("/gk/schoolDetails")
public class SchoolDetailsController extends BaseController
{
    @Autowired
    private ISchoolDetailsService schoolDetailsService;

    /**
     * 查询学校详情列表
     */
    @PreAuthorize("@ss.hasPermi('gk:schoolDetails:list')")
    @GetMapping("/list")
    public TableDataInfo list(SchoolDetails schoolDetails)
    {
        startPage();
        List<SchoolDetails> list = schoolDetailsService.selectSchoolDetailsList(schoolDetails);
        return getDataTable(list);
    }

    /**
     * 导出学校详情列表
     */
    @PreAuthorize("@ss.hasPermi('gk:schoolDetails:export')")
    @Log(title = "学校详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolDetails schoolDetails)
    {
        List<SchoolDetails> list = schoolDetailsService.selectSchoolDetailsList(schoolDetails);
        ExcelUtil<SchoolDetails> util = new ExcelUtil<>(SchoolDetails.class);
        util.exportExcel(response, list, "学校详情数据");
    }

    /**
     * 获取学校详情详细信息
     */
    @Anonymous
    @GetMapping(value = "/{id}")
    public RestBean<SchoolDetailsVo> getInfo(@PathVariable("id") Long id)
    {
        return RestBean.Success(schoolDetailsService.selectSchoolDetailsById(id));
    }

    /**
     * 新增学校详情
     */
    @PreAuthorize("@ss.hasPermi('gk:schoolDetails:add')")
    @Log(title = "学校详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolDetails schoolDetails)
    {
        return toAjax(schoolDetailsService.insertSchoolDetails(schoolDetails));
    }

    /**
     * 修改学校详情
     */
    @PreAuthorize("@ss.hasPermi('gk:schoolDetails:edit')")
    @Log(title = "学校详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolDetails schoolDetails)
    {
        return toAjax(schoolDetailsService.updateSchoolDetails(schoolDetails));
    }

    /**
     * 删除学校详情
     */
    @PreAuthorize("@ss.hasPermi('gk:schoolDetails:remove')")
    @Log(title = "学校详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(schoolDetailsService.deleteSchoolDetailsByIds(ids));
    }
}
