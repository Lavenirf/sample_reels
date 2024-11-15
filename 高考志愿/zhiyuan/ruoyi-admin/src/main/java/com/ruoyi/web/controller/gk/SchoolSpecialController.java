package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.gk.domain.SchoolSpecial;
import com.ruoyi.gk.service.ISchoolSpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学校专业Controller
 *
 * @author ruoyi
 * @date 2024-06-22
 */
@RestController
@RequestMapping("/gk/special")
public class SchoolSpecialController extends BaseController
{
    @Autowired
    private ISchoolSpecialService schoolSpecialService;

    /**
     * 查询学校专业列表
     */

    @Anonymous
    @GetMapping("/list")
    public RestBean<TableDataInfo> list(SchoolSpecial schoolSpecial)
    {
        startPage();
        List<SchoolSpecial> list = schoolSpecialService.selectSchoolSpecialList(schoolSpecial);
        TableDataInfo dataTable = getDataTable(list);
        return RestBean.Success(dataTable);
    }
    /**
     * 查询学校专业列表
     */
    @Anonymous
    @GetMapping("/search")
    public RestBean< List<SchoolSpecial>> search(@RequestParam("name") String name)
    {
        return RestBean.Success(schoolSpecialService.selectSpecialByName(name));
    }


    /**
     * 导出学校专业列表
     */

    @Log(title = "学校专业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolSpecial schoolSpecial)
    {
        List<SchoolSpecial> list = schoolSpecialService.selectSchoolSpecialList(schoolSpecial);
        ExcelUtil<SchoolSpecial> util = new ExcelUtil<>(SchoolSpecial.class);
        util.exportExcel(response, list, "学校专业数据");
    }

    /**
     * 获取学校专业详细信息
     */

    @GetMapping(value = "/{specialId}")
    public RestBean<SchoolSpecial> getInfo(@PathVariable("specialId") Long specialId)
    {
        return RestBean.Success(schoolSpecialService.selectSchoolSpecialBySpecialId(specialId));
    }

    /**
     * 新增学校专业
     */

    @Log(title = "学校专业", businessType = BusinessType.INSERT)
    @PostMapping
    public RestBean<Integer> add(@RequestBody SchoolSpecial schoolSpecial)
    {
        return RestBean.Success(schoolSpecialService.insertSchoolSpecial(schoolSpecial));
    }

    /**
     * 修改学校专业
     */

    @Log(title = "学校专业", businessType = BusinessType.UPDATE)
    @PutMapping
    public RestBean<Integer> edit(@RequestBody SchoolSpecial schoolSpecial)
    {
        return RestBean.Success(schoolSpecialService.updateSchoolSpecial(schoolSpecial));
    }

    /**
     * 删除学校专业
     */

    @Log(title = "学校专业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{specialIds}")
    public RestBean<Integer> remove(@PathVariable Long[] specialIds)
    {
        return RestBean.Success(schoolSpecialService.deleteSchoolSpecialBySpecialIds(specialIds));
    }
}
