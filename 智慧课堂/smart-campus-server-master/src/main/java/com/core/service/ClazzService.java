package com.core.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.Clazz;

/**
 * 班级信息业务层接口
 *
 * @author zph
 * @date 2023-07-14 19:35:37
 */
public interface ClazzService {

    /**
     * 查询班级信息
     *
     * @param id 班级信息主键
     * @return 班级信息
     */
     Clazz selectClazzById(Long id);

    /**
     * 分页查询班级信息列表
     *
     * @param clazz 班级信息
     * @return 班级信息集合
     */
    IPage<Clazz> selectClazzPage(Clazz clazz);

    /**
     * 查询班级信息列表
     *
     * @param clazz 班级信息
     * @return 班级信息集合
     */
    List<Clazz> selectClazzList(Clazz clazz);

    /**
     * 新增班级信息
     *
     * @param clazz 班级信息
     * @return 结果
     */
    void insertClazz(Clazz clazz);

    /**
     * 修改班级信息
     *
     * @param clazz 班级信息
     * @return 结果
     */
    void updateClazz(Clazz clazz);

    /**
     * 批量删除班级信息
     *
     * @param ids 需要删除的班级信息主键集合
     * @return 结果
     */
    void deleteClazzByIds(Long[] ids);

    /**
     * 删除班级信息信息
     *
     * @param id 班级信息主键
     * @return 结果
     */
    void deleteClazzById(Long id);
}
