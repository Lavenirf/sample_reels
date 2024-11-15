package com.ruoyi.web.controller.gk;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ruoyi.common.core.domain.CodeState;
import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.gk.domain.VolunteerForm;
import com.ruoyi.gk.domain.VolunteerFormDetails;
import com.ruoyi.gk.domain.vo.VolunteerFormDetailsExportVo;
import com.ruoyi.gk.domain.vo.VolunteerFormDetailsVo;
import com.ruoyi.gk.mapper.VolunteerFormMapper;
import com.ruoyi.gk.service.IVolunteerFormDetailsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 志愿详情Controller
 *
 * @author ruoyi
 * @date 2024-07-16
 */
@RestController
@RequestMapping("/gk/volunteerFormDetails")
public class VolunteerFormDetailsController extends BaseController {
    @Autowired
    private IVolunteerFormDetailsService volunteerFormDetailsService;
    @Autowired
    private VolunteerFormMapper volunteerFormMapper;

    /**
     * 查询志愿详情列表
     */
    @PreAuthorize("@ss.hasPermi('gk:details:list')")
    @GetMapping("/list")
    public RestBean<TableDataInfo> list(VolunteerFormDetails volunteerFormDetails) {
        startPage();
        List<VolunteerFormDetails> list = volunteerFormDetailsService.selectVolunteerFormDetailsList(volunteerFormDetails);
        return RestBean.Success(getDataTable(list));
    }

    /**
     * 导出志愿详情列表
     */
    @PreAuthorize("@ss.hasPermi('gk:details:export')")
    @Log(title = "志愿详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestParam("voId") Integer voId,@RequestParam("paragraphId") Integer paragraphId) throws IOException {
        List<VolunteerFormDetailsVo> list = volunteerFormDetailsService.selectVolunteerFormDetailsByVoId(voId, paragraphId);
        if (list.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        // 按照 paragraph 字段分组
        Map<Integer, List<VolunteerFormDetailsExportVo>> groupedExportList = list.stream()
                .map(u -> {
                    VolunteerFormDetailsExportVo exportVo = new VolunteerFormDetailsExportVo();
                    exportVo.setSchoolName(u.getSchoolName());
                    exportVo.setNatureName(u.getNatureName());
                    exportVo.setCodeEnroll(u.getCodeEnroll());
                    exportVo.setSpecialName(u.getSpecialName());
                    exportVo.setTypeName(u.getTypeName());
                    exportVo.setLimitYear(u.getLimitYear());
                    exportVo.setOrder(u.getOrder());
                    exportVo.setParagraph(u.getParagraph()); // Assuming paragraph field is added in VolunteerFormDetailsExportVo
                    return exportVo;
                })
                .collect(Collectors.groupingBy(VolunteerFormDetailsExportVo::getParagraph));

        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 删除默认的 "sheet1"
        writer.getWorkbook().removeSheetAt(0);

        // 自定义标题别名
        writer.addHeaderAlias("id", "序号");
        writer.addHeaderAlias("schoolName", "院校");
        writer.addHeaderAlias("natureName", "类型");
        writer.addHeaderAlias("codeEnroll", "院校代码");
        writer.addHeaderAlias("specialName", "专业名称");
        writer.addHeaderAlias("typeName", "所属类别");
        writer.addHeaderAlias("limitYear", "休学年限");
        writer.addHeaderAlias("order", "志愿顺序");
        writer.addHeaderAlias("paragraph", "普通类");

        // 分别写入不同的 Sheet
        groupedExportList.forEach((paragraph, exportList) -> {
            if (paragraph == 1) {
                writer.setSheet("普通类一段");
            } else if (paragraph == 2) {
                writer.setSheet("普通类二段");
            }

            // 重置序号
            Long[] counter = {1L};
            exportList.forEach(u -> u.setId(counter[0]++));

            // 写入数据，只包含定义了别名的字段
            writer.write(exportList, true);
        });
        VolunteerForm volunteerForm = volunteerFormMapper.selectVolunteerFormById(Long.valueOf(voId));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(volunteerForm.getName() + DateUtil.today() + ".xlsx", "utf-8"));

        try (ServletOutputStream out = response.getOutputStream()) {
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    /**
     * 获取志愿详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('gk:details:query')")
    @GetMapping(value = "/getDetails")
    public RestBean<List<VolunteerFormDetailsVo>> getInfo(@RequestParam("voId") Integer voId, @RequestParam("paragraph") Integer paragraph) {
        return RestBean.Success(volunteerFormDetailsService.selectVolunteerFormDetailsByVoId(voId, paragraph));
    }

    /**
     * 新增志愿详情
     */
    @PreAuthorize("@ss.hasPermi('gk:details:add')")
    @Log(title = "志愿详情", businessType = BusinessType.INSERT)
    @PostMapping
    public RestBean<Integer> add(@RequestBody VolunteerFormDetails volunteerFormDetails) {
        int i = volunteerFormDetailsService.insertVolunteerFormDetails(volunteerFormDetails);
        if (i == 0) {
            return RestBean.Error(CodeState.PARAMETERError);
        } else if (i == 2) {
            return RestBean.Error(2, "当前志愿表已满");
        } else if (i == 3) {
            return RestBean.Error(3, "志愿重复");
        } else {
            return RestBean.Success();
        }
    }

    /**
     * 修改志愿详情
     */
    @PreAuthorize("@ss.hasPermi('gk:details:edit')")
    @Log(title = "志愿详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public RestBean<Integer> edit(@RequestBody VolunteerFormDetails volunteerFormDetails, @RequestParam(value = "oldOrder") Integer oldOrder) {
        if (volunteerFormDetailsService.updateVolunteerFormDetails(volunteerFormDetails, oldOrder) == 0) {
            return RestBean.Error(400, "缺少必要参数");
        }
        return RestBean.Success();
    }

    /**
     * 删除志愿详情
     */
    @PreAuthorize("@ss.hasPermi('gk:details:remove')")
    @Log(title = "志愿详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public RestBean<Integer> remove(@PathVariable Long id) {
        return RestBean.Success(volunteerFormDetailsService.deleteVolunteerFormDetailsById(id));
    }
}
