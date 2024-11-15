package com.core.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.Score;

/**
 * 成绩信息业务层接口
 *
 * @author zph
 * @date 2023-07-14 19:35:32
 */
public interface ScoreService {

    /**
     * 查询成绩信息
     *
     * @param id 成绩信息主键
     * @return 成绩信息
     */
     Score selectScoreById(Long id);

    /**
     * 分页查询成绩信息列表
     *
     * @param score 成绩信息
     * @return 成绩信息集合
     */
    IPage<Score> selectScorePage(Score score);

    /**
     * 查询成绩信息列表
     *
     * @param score 成绩信息
     * @return 成绩信息集合
     */
    List<Score> selectScoreList(Score score);

    /**
     * 上传成绩信息
     * @param scores 成绩信息
     * @return 结果
     */
    void updateScoreByStudentExamId(String examId, List<Score> scores);

    /**
     * 新增成绩信息
     *
     * @param score 成绩信息
     * @return 结果
     */
    void insertScore(Score score);

    /**
     * 修改成绩信息
     *
     * @param score 成绩信息
     * @return 结果
     */
    void updateScore(Score score);

    /**
     * 批量删除成绩信息
     *
     * @param ids 需要删除的成绩信息主键集合
     * @return 结果
     */
    void deleteScoreByIds(Long[] ids);

    /**
     * 删除成绩信息信息
     *
     * @param id 成绩信息主键
     * @return 结果
     */
    void deleteScoreById(Long id);


}
