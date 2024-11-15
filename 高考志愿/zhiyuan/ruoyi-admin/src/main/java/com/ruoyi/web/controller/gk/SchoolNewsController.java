package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.gk.domain.SchoolNews;
import com.ruoyi.gk.service.ISchoolNewsService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * newsController
 * 
 * @author wbl
 * @date 2024-08-03
 */
@RestController
@RequestMapping("/gk/news")
public class SchoolNewsController extends BaseController
{
    @Autowired
    private ISchoolNewsService schoolNewsService;

    /**
     * 查询news列表
     */
    @Anonymous
    @GetMapping("/list")
    public RestBean<TableDataInfo> list(SchoolNews schoolNews)
    {
        startPage();
        List<SchoolNews> list = schoolNewsService.selectSchoolNewsList(schoolNews);
        return RestBean.Success(getDataTable(list));
    }

    /**
     * 导出news列表
     */
    @PreAuthorize("@ss.hasPermi('news:news:export')")
    @Log(title = "news", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolNews schoolNews)
    {
        List<SchoolNews> list = schoolNewsService.selectSchoolNewsList(schoolNews);
        ExcelUtil<SchoolNews> util = new ExcelUtil<>(SchoolNews.class);
        util.exportExcel(response, list, "news数据");
    }

    /**
     * 获取news详细信息
     */
    @PreAuthorize("@ss.hasPermi('news:news:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(schoolNewsService.selectSchoolNewsById(id));
    }

    /**
     * 新增news
     */
    @PreAuthorize("@ss.hasPermi('news:news:add')")
    @Log(title = "news", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolNews schoolNews)
    {
        return toAjax(schoolNewsService.insertSchoolNews(schoolNews));
    }

    /**
     * 修改news
     */
    @PreAuthorize("@ss.hasPermi('news:news:edit')")
    @Log(title = "news", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolNews schoolNews)
    {
        return toAjax(schoolNewsService.updateSchoolNews(schoolNews));
    }

    /**
     * 删除news
     */
    @PreAuthorize("@ss.hasPermi('news:news:remove')")
    @Log(title = "news", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(schoolNewsService.deleteSchoolNewsByIds(ids));
    }
}
