package com.core.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.Exam;

/**
 * 考试信息业务层接口
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
public interface ExamService {

    /**
     * 查询考试信息
     *
     * @param id 考试信息主键
     * @return 考试信息
     */
     Exam selectExamById(Long id);

    /**
     * 分页查询考试信息列表
     *
     * @param exam 考试信息
     * @return 考试信息集合
     */
    IPage<Exam> selectExamPage(Exam exam);

    /**
     * 通过学生id查询考试信息列表
     */
    List<Exam> studentExamList(Long studentId);

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
     * 批量删除考试信息
     *
     * @param ids 需要删除的考试信息主键集合
     * @return 结果
     */
    void deleteExamByIds(Long[] ids);

    /**
     * 删除考试信息信息
     *
     * @param id 考试信息主键
     * @return 结果
     */
    void deleteExamById(Long id);

}
