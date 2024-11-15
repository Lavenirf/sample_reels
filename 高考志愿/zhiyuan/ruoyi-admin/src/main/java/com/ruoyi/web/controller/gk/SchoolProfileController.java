package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
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
import com.ruoyi.gk.domain.SchoolProfile;
import com.ruoyi.gk.service.ISchoolProfileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * schoolProfileController
 * 
 * @author wbl
 * @date 2024-08-03
 */
@RestController
@RequestMapping("/gk/profile")
public class SchoolProfileController extends BaseController
{
    @Autowired
    private ISchoolProfileService schoolProfileService;

    /**
     * 查询schoolProfile列表
     */
    @PreAuthorize("@ss.hasPermi('schoolProfile:profile:list')")
    @GetMapping("/list")
    public TableDataInfo list(SchoolProfile schoolProfile)
    {
        startPage();
        List<SchoolProfile> list = schoolProfileService.selectSchoolProfileList(schoolProfile);
        return getDataTable(list);
    }

    /**
     * 导出schoolProfile列表
     */
    @PreAuthorize("@ss.hasPermi('schoolProfile:profile:export')")
    @Log(title = "schoolProfile", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolProfile schoolProfile)
    {
        List<SchoolProfile> list = schoolProfileService.selectSchoolProfileList(schoolProfile);
        ExcelUtil<SchoolProfile> util = new ExcelUtil<>(SchoolProfile.class);
        util.exportExcel(response, list, "schoolProfile数据");
    }

    /**
     * 获取schoolProfile详细信息
     */
    @Anonymous
    @GetMapping(value = "/{id}")
    public RestBean<SchoolProfile> getInfo(@PathVariable("id") Long id)
    {
        return RestBean.Success(schoolProfileService.selectSchoolProfileById(id));
    }

    /**
     * 新增schoolProfile
     */
    @PreAuthorize("@ss.hasPermi('schoolProfile:profile:add')")
    @Log(title = "schoolProfile", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolProfile schoolProfile)
    {
        return toAjax(schoolProfileService.insertSchoolProfile(schoolProfile));
    }

    /**
     * 修改schoolProfile
     */
    @PreAuthorize("@ss.hasPermi('schoolProfile:profile:edit')")
    @Log(title = "schoolProfile", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolProfile schoolProfile)
    {
        return toAjax(schoolProfileService.updateSchoolProfile(schoolProfile));
    }

    /**
     * 删除schoolProfile
     */
    @PreAuthorize("@ss.hasPermi('schoolProfile:profile:remove')")
    @Log(title = "schoolProfile", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(schoolProfileService.deleteSchoolProfileByIds(ids));
    }
}
