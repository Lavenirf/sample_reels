package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
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
import com.ruoyi.gk.domain.SchoolMajorScores;
import com.ruoyi.gk.service.ISchoolMajorScoresService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学校各专业录取分数线及位次Controller
 *
 * @author ruoyi
 * @date 2024-07-18
 */
@RestController
@RequestMapping("/gk/mscores")
public class SchoolMajorScoresController extends BaseController
{
    @Autowired
    private ISchoolMajorScoresService schoolMajorScoresService;

    /**
     * 查询学校各专业录取分数线及位次列表
     */
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(SchoolMajorScores schoolMajorScores)
    {
        startPage();
        List<SchoolMajorScores> list = schoolMajorScoresService.selectSchoolMajorScoresList(schoolMajorScores);
        return getDataTable(list);
    }

    /**
     * 导出学校各专业录取分数线及位次列表
     */
    @PreAuthorize("@ss.hasPermi('gk:mscores:export')")
    @Log(title = "学校各专业录取分数线及位次", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolMajorScores schoolMajorScores)
    {
        List<SchoolMajorScores> list = schoolMajorScoresService.selectSchoolMajorScoresList(schoolMajorScores);
        ExcelUtil<SchoolMajorScores> util = new ExcelUtil<SchoolMajorScores>(SchoolMajorScores.class);
        util.exportExcel(response, list, "学校各专业录取分数线及位次数据");
    }

    /**
     * 获取学校各专业录取分数线及位次详细信息
     */
    @PreAuthorize("@ss.hasPermi('gk:mscores:query')")
    @GetMapping(value = "/{mcid}")
    public AjaxResult getInfo(@PathVariable("mcid") Long mcid)
    {
        return success(schoolMajorScoresService.selectSchoolMajorScoresByMcid(mcid));
    }

    /**
     * 新增学校各专业录取分数线及位次
     */
    @PreAuthorize("@ss.hasPermi('gk:mscores:add')")
    @Log(title = "学校各专业录取分数线及位次", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolMajorScores schoolMajorScores)
    {
        return toAjax(schoolMajorScoresService.insertSchoolMajorScores(schoolMajorScores));
    }

    /**
     * 修改学校各专业录取分数线及位次
     */
    @PreAuthorize("@ss.hasPermi('gk:mscores:edit')")
    @Log(title = "学校各专业录取分数线及位次", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolMajorScores schoolMajorScores)
    {
        return toAjax(schoolMajorScoresService.updateSchoolMajorScores(schoolMajorScores));
    }

    /**
     * 删除学校各专业录取分数线及位次
     */
    @PreAuthorize("@ss.hasPermi('gk:mscores:remove')")
    @Log(title = "学校各专业录取分数线及位次", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mcids}")
    public AjaxResult remove(@PathVariable Long[] mcids)
    {
        return toAjax(schoolMajorScoresService.deleteSchoolMajorScoresByMcids(mcids));
    }
}
