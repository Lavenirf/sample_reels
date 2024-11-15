package com.core.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.Homework;

/**
 * 作业信息业务层接口
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
public interface HomeworkService {

    /**
     * 查询作业信息
     *
     * @param id 作业信息主键
     * @return 作业信息
     */
     Homework selectHomeworkById(Long id);

    /**
     * 分页查询作业信息列表
     *
     * @param homework 作业信息
     * @return 作业信息集合
     */
    IPage<Homework> selectHomeworkPage(Homework homework);

    /**
     * 查询作业信息列表
     *
     * @param homework 作业信息
     * @return 作业信息集合
     */
    List<Homework> selectHomeworkList(Homework homework);

    /**
     * 新增作业信息
     *
     * @param homework 作业信息
     * @return 结果
     */
    void insertHomework(Homework homework);

    /**
     * 修改作业信息
     *
     * @param homework 作业信息
     * @return 结果
     */
    void updateHomework(Homework homework);

    /**
     * 批量删除作业信息
     *
     * @param ids 需要删除的作业信息主键集合
     * @return 结果
     */
    void deleteHomeworkByIds(Long[] ids);

    /**
     * 删除作业信息信息
     *
     * @param id 作业信息主键
     * @return 结果
     */
    void deleteHomeworkById(Long id);
}
