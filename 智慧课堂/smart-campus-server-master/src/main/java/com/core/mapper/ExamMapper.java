package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.Exam;

/**
 * 考试信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
public interface ExamMapper extends BaseMapper<Exam> {

    /**
     * 查询考试信息
     *
     * @param id 考试信息主键
     * @return 考试信息
     */
    Exam selectExamById(Long id);

    /**
     * 查询考试信息列表
     *
     * @param exam 考试信息
     * @return 考试信息集合
     */
    List<Exam> selectExamList(Exam exam);

    /**
     * 新增考试信息
     *
     * @param exam 考试信息
     * @return 结果
     */
    void insertExam(Exam exam);

    /**
     * 修改考试信息
     *
     * @param exam 考试信息
     * @return 结果
     */
    void updateExam(Exam exam);

    /**
     * 删除考试信息
     *
     * @param id 考试信息主键
     * @return 结果
     */
    void deleteExamById(Long id);

    /**
     * 批量删除考试信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteExamByIds(Long[] ids);
}
