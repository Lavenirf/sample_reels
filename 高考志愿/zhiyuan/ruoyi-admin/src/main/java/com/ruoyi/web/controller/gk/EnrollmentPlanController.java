package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.gk.domain.vo.EnrollmentPlanVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.gk.domain.EnrollmentPlan;
import com.ruoyi.gk.service.IEnrollmentPlanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * enrollmentPlanController
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
@RestController
@RequestMapping("/gk/enrollmentPlan")
public class EnrollmentPlanController extends BaseController
{
    @Autowired
    private IEnrollmentPlanService enrollmentPlanService;

    /**
     * 查询enrollmentPlan列表
     */
    @Anonymous
    @GetMapping("/list")
    public RestBean<TableDataInfo> list(EnrollmentPlan enrollmentPlan)
    {
        startPage();
        List<EnrollmentPlan> list = enrollmentPlanService.selectEnrollmentPlanList(enrollmentPlan);
        return RestBean.Success(getDataTable(list));
    }
    /**
     * 查询enrollmentPlan差距列表
     */
    @PreAuthorize("@ss.hasPermi('gk:enrollmentPlan:list')")
    @PostMapping("/gapList")
    public RestBean< List<List<EnrollmentPlanVo>>> gapList(@RequestBody List<EnrollmentPlanVo> enrollmentPlanList)
    {
        List<List<EnrollmentPlanVo>> list = enrollmentPlanService.selectEnrollmentPlanGapList(enrollmentPlanList);
        return RestBean.Success(list);
    }

    /**
     * 导出enrollmentPlan列表
     */
    @PreAuthorize("@ss.hasPermi('gk:enrollmentPlan:export')")
    @Log(title = "enrollmentPlan", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnrollmentPlan enrollmentPlan)
    {
        List<EnrollmentPlan> list = enrollmentPlanService.selectEnrollmentPlanList(enrollmentPlan);
        ExcelUtil<EnrollmentPlan> util = new ExcelUtil<EnrollmentPlan>(EnrollmentPlan.class);
        util.exportExcel(response, list, "enrollmentPlan数据");
    }

    /**
     * 获取enrollmentPlan详细信息
     */
    @PreAuthorize("@ss.hasPermi('gk:enrollmentPlan:query')")
    @GetMapping(value = "/{schoolId}")
    public AjaxResult getInfo(@PathVariable("schoolId") String schoolId)
    {
        return success(enrollmentPlanService.selectEnrollmentPlanBySchoolId(schoolId));
    }

    /**
     * 新增enrollmentPlan
     */
    @PreAuthorize("@ss.hasPermi('gk:enrollmentPlan:add')")
    @Log(title = "enrollmentPlan", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnrollmentPlan enrollmentPlan)
    {
        return toAjax(enrollmentPlanService.insertEnrollmentPlan(enrollmentPlan));
    }

    /**
     * 修改enrollmentPlan
     */
    @PreAuthorize("@ss.hasPermi('gk:enrollmentPlan:edit')")
    @Log(title = "enrollmentPlan", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnrollmentPlan enrollmentPlan)
    {
        return toAjax(enrollmentPlanService.updateEnrollmentPlan(enrollmentPlan));
    }

    /**
     * 删除enrollmentPlan
     */
    @PreAuthorize("@ss.hasPermi('gk:enrollmentPlan:remove')")
    @Log(title = "enrollmentPlan", businessType = BusinessType.DELETE)
	@DeleteMapping("/{schoolIds}")
    public AjaxResult remove(@PathVariable String[] schoolIds)
    {
        return toAjax(enrollmentPlanService.deleteEnrollmentPlanBySchoolIds(schoolIds));
    }
}
