package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.gk.mapper.RecommendedMajorsMapper;
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
import com.ruoyi.gk.domain.RecommendedMajors;
import com.ruoyi.gk.service.IRecommendedMajorsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 推荐专业Controller
 *
 * @author cyz
 * @date 2024-07-19
 */
@RestController
@RequestMapping("/gk/majors")
public class RecommendedMajorsController extends BaseController
{
    @Autowired
    private IRecommendedMajorsService recommendedMajorsService;

    @Autowired
    private RecommendedMajorsMapper recommendedMajorsMapper;

    /**
     * 查询推荐专业列表
     */

    @GetMapping("/list")
    public TableDataInfo list(RecommendedMajors recommendedMajors)
    {
        startPage();
        List<RecommendedMajors> list = recommendedMajorsService.selectRecommendedMajorsList(recommendedMajors);
        return getDataTable(list);
    }

    @GetMapping("/bao")
    public TableDataInfo bao(RecommendedMajors recommendedMajors)
    {
        startPage();
        List<RecommendedMajors> list = recommendedMajorsMapper.selectRecommendedMajorsbao(recommendedMajors);
        return getDataTable(list);
    }
    @GetMapping("/wen")
    public TableDataInfo wen(RecommendedMajors recommendedMajors)
    {
        startPage();
        List<RecommendedMajors> list = recommendedMajorsMapper.selectRecommendedMajorswen(recommendedMajors);
        return getDataTable(list);
    }
    @GetMapping("/chong")
    public TableDataInfo chong(RecommendedMajors recommendedMajors)
    {
        startPage();
        List<RecommendedMajors> list = recommendedMajorsMapper.selectRecommendedMajorschong(recommendedMajors);
        return getDataTable(list);
    }

    /**
     * 导出推荐专业列表
     */

    @Log(title = "推荐专业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RecommendedMajors recommendedMajors)
    {
        List<RecommendedMajors> list = recommendedMajorsService.selectRecommendedMajorsList(recommendedMajors);
        ExcelUtil<RecommendedMajors> util = new ExcelUtil<RecommendedMajors>(RecommendedMajors.class);
        util.exportExcel(response, list, "推荐专业数据");
    }

    /**
     * 获取推荐专业详细信息
     */

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(recommendedMajorsService.selectRecommendedMajorsById(id));
    }

    /**
     * 新增推荐专业
     */

    @Log(title = "推荐专业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RecommendedMajors recommendedMajors)
    {
        return toAjax(recommendedMajorsService.insertRecommendedMajors(recommendedMajors));
    }

    /**
     * 修改推荐专业
     */

    @Log(title = "推荐专业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RecommendedMajors recommendedMajors)
    {
        return toAjax(recommendedMajorsService.updateRecommendedMajors(recommendedMajors));
    }

    /**
     * 删除推荐专业
     */

    @Log(title = "推荐专业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(recommendedMajorsService.deleteRecommendedMajorsByIds(ids));
    }
}
