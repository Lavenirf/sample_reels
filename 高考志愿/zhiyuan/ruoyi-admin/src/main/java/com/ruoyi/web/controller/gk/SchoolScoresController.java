package com.ruoyi.web.controller.gk;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
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
import com.ruoyi.gk.domain.SchoolScores;
import com.ruoyi.gk.service.ISchoolScoresService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学校录取分数线Controller
 *
 * @author cyz
 * @date 2024-07-16
 */
@RestController
@RequestMapping("/gk/scores")
public class SchoolScoresController extends BaseController
{
    @Autowired
    private ISchoolScoresService schoolScoresService;

    /**
     * 查询学校录取分数线列表
     */
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(SchoolScores schoolScores)
    {
        startPage();
        List<SchoolScores> list = schoolScoresService.selectSchoolScoresList(schoolScores);
        return getDataTable(list);
    }

    /**
     * 导出学校录取分数线列表
     */

    @Log(title = "学校录取分数线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolScores schoolScores)
    {
        List<SchoolScores> list = schoolScoresService.selectSchoolScoresList(schoolScores);
        ExcelUtil<SchoolScores> util = new ExcelUtil<>(SchoolScores.class);
        util.exportExcel(response, list, "学校录取分数线数据");
    }

    /**
     * 获取学校录取分数线详细信息
     */

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(schoolScoresService.selectSchoolScoresById(id));
    }

    /**
     * 新增学校录取分数线
     */

    @Log(title = "学校录取分数线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolScores schoolScores)
    {
        return toAjax(schoolScoresService.insertSchoolScores(schoolScores));
    }

    /**
     * 修改学校录取分数线
     */

    @Log(title = "学校录取分数线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolScores schoolScores)
    {
        return toAjax(schoolScoresService.updateSchoolScores(schoolScores));
    }

    /**
     * 删除学校录取分数线
     */

    @Log(title = "学校录取分数线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(schoolScoresService.deleteSchoolScoresByIds(ids));
    }
}
