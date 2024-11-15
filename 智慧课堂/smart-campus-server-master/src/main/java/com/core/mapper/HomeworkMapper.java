package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.Homework;

/**
 * 作业信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
public interface HomeworkMapper extends BaseMapper<Homework> {

    /**
     * 查询作业信息
     *
     * @param id 作业信息主键
     * @return 作业信息
     */
    Homework selectHomeworkById(Long id);

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
     * 删除作业信息
     *
     * @param id 作业信息主键
     * @return 结果
     */
    void deleteHomeworkById(Long id);

    /**
     * 批量删除作业信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteHomeworkByIds(Long[] ids);
}
