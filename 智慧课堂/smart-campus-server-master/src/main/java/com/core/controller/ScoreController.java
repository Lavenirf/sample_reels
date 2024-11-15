package com.core.controller;

import java.io.IOException;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;

import com.core.utils.ExcelUtils;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.core.entity.Score;
import com.core.service.ScoreService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 成绩信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:32
 */
@Api(tags = "成绩信息控制层")
@RestController
@RequestMapping("/api/score")
public class ScoreController extends BaseController {

    @Resource
    private ScoreService scoreService;

    /**
     * 获取成绩信息详细信息
     */
    @ApiOperation(value = "获取成绩信息详细信息", notes = "获取成绩信息详细信息", response = Score.class)
    @GetMapping(value = "/{id}")
    public Score getInfo(@PathVariable("id") Long id) {
        return scoreService.selectScoreById(id);
    }

    /**
     * 分页查询成绩信息列表
     */
    @ApiOperation(value = "分页查询成绩信息列表", notes = "分页查询成绩信息列表", response = Score.class)
    @GetMapping("/page")
    public IPage<Score> page(Score score) {
        IPage<Score> page = scoreService.selectScorePage(score);
        return page;
    }

    /**
     * 查询成绩信息列表
     */
    @ApiOperation(value = "查询成绩信息列表", notes = "查询成绩信息列表", response = Score.class)
    @GetMapping("/list")
    public List<Score> list(Score score) {
        List<Score> list = scoreService.selectScoreList(score);
        return list;
    }

    /**
     * 上传成绩信息
     */
    @ApiOperation(value = "上传成绩信息", notes = "上传成绩信息")
    @PostMapping("/import")
    public void uploadExcel(@RequestParam String examId, @RequestParam("file") MultipartFile file) throws IOException {
        // 1. 解析Excel文件并获取数据
        List<Score> scores = ExcelUtils.parseScoreExcel(file.getInputStream());
        // 2. 保存数据到数据库
        scoreService.updateScoreByStudentExamId(examId, scores);
    }

    /**
     * 新增成绩信息
     */
    @ApiOperation(value = "新增成绩信息", notes = "新增成绩信息")
    @PostMapping
    public void add(@RequestBody Score score) {
        scoreService.insertScore(score);
    }

    /**
     * 修改成绩信息
     */
    @ApiOperation(value = "修改成绩信息", notes = "修改成绩信息")
    @PutMapping
    public void edit(@RequestBody Score score) {
        scoreService.updateScore(score);
    }

    /**
     * 删除成绩信息
     */
    @ApiOperation(value = "删除成绩信息", notes = "删除成绩信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        scoreService.deleteScoreByIds(ids);
    }

}
