package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.gk.domain.VolunteerForm;
import com.ruoyi.gk.service.IVolunteerFormService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 志愿Controller
 * 
 * @author ruoyi
 * @date 2024-07-16
 */
@RestController
@RequestMapping("/gk/volunteerForm")
public class VolunteerFormController extends BaseController
{
    @Autowired
    private IVolunteerFormService volunteerFormService;

    /**
     * 查询志愿列表
     */
    @PreAuthorize("@ss.hasPermi('gk:form:list')")
    @GetMapping("/list")
    public RestBean<TableDataInfo> list(VolunteerForm volunteerForm)
    {
        startPage();
        List<VolunteerForm> list = volunteerFormService.selectVolunteerFormList(volunteerForm);
        return RestBean.Success(getDataTable(list));
    }

    /**
     * 导出志愿列表
     */
    @PreAuthorize("@ss.hasPermi('gk:form:export')")
    @Log(title = "志愿", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VolunteerForm volunteerForm)
    {
        List<VolunteerForm> list = volunteerFormService.selectVolunteerFormList(volunteerForm);
        ExcelUtil<VolunteerForm> util = new ExcelUtil<>(VolunteerForm.class);
        util.exportExcel(response, list, "志愿数据");
    }
    /**
     * 获取使用志愿信息
     */
    @PreAuthorize("@ss.hasPermi('gk:form:query')")
    @GetMapping()
    public RestBean<VolunteerForm> getInfo(@RequestParam("id") Long id)
    {
        return RestBean.Success(volunteerFormService.selectVolunteerFormById(id));
    }

    /**
     * 获取用户志愿信息
     */
    @PreAuthorize("@ss.hasPermi('gk:form:query')")
    @GetMapping(value = "/{user_id}")
    public RestBean<List<VolunteerForm>> getUserInfo(@PathVariable("user_id") Long id)
    {
        return RestBean.Success(volunteerFormService.selectVolunteerFormByUserId(id));
    }

    /**
     * 新增志愿
     */
    @PreAuthorize("@ss.hasPermi('gk:form:add')")
    @Log(title = "志愿", businessType = BusinessType.INSERT)
    @PostMapping
    public RestBean<Integer> add(@RequestBody VolunteerForm volunteerForm)
    {
        return RestBean.Success(Math.toIntExact(volunteerFormService.insertVolunteerForm(volunteerForm)));
    }

    /**
     * 修改志愿
     */
    @PreAuthorize("@ss.hasPermi('gk:form:edit')")
    @Log(title = "志愿", businessType = BusinessType.UPDATE)
    @PutMapping
    public RestBean<Integer> edit(@RequestBody VolunteerForm volunteerForm)
    {
        return RestBean.Success(volunteerFormService.updateVolunteerForm(volunteerForm));
    }

    /**
     * 删除志愿
     */
    @PreAuthorize("@ss.hasPermi('gk:form:remove')")
    @Log(title = "志愿", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public RestBean<Integer> remove(@PathVariable Long id)
    {
        return RestBean.Success(volunteerFormService.deleteVolunteerFormById(id));
    }
}
