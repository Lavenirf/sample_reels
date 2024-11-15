package com.core.service.impl;

import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

import com.core.entity.ClazzRoom;
import org.springframework.stereotype.Service;
import com.core.mapper.ClazzMapper;
import com.core.entity.Clazz;
import com.core.service.ClazzService;

/**
 * 班级信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:37
 */
@Service
public class ClazzServiceImpl implements ClazzService {

    @Resource
    private ClazzMapper clazzMapper;

    /**
     * 查询班级信息
     *
     * @param id 班级信息主键
     * @return 班级信息
     */
    @Override
    public Clazz selectClazzById(Long id) {
        return clazzMapper.selectClazzById(id);
    }

    /**
     * 分页查询班级信息列表
     *
     * @param clazz 班级信息
     * @return 班级信息
     */
    @Override
    public IPage<Clazz> selectClazzPage(Clazz clazz) {
        IPage<Clazz> iPage = new Page<>(clazz.getPage(), clazz.getPageSize());
        LambdaQueryWrapper<Clazz> lambdaQueryWrapper = new LambdaQueryWrapper<>(clazz);
        lambdaQueryWrapper.orderByDesc(Clazz::getCreateTime);
        return clazzMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询班级信息列表
     *
     * @param clazz 班级信息
     * @return 班级信息
     */
    @Override
    public List<Clazz> selectClazzList(Clazz clazz) {
        return clazzMapper.selectClazzList(clazz);
    }

    /**
     * 新增班级信息
     *
     * @param clazz 班级信息
     * @return 结果
     */
    @Override
    public void insertClazz(Clazz clazz) {
        clazzMapper.insertClazz(clazz);
    }

    /**
     * 修改班级信息
     *
     * @param clazz 班级信息
     * @return 结果
     */
    @Override
    public void updateClazz(Clazz clazz) {
        clazzMapper.updateClazz(clazz);
    }

    /**
     * 批量删除班级信息
     *
     * @param ids 需要删除的班级信息主键
     * @return 结果
     */
    @Override
    public void deleteClazzByIds(Long[] ids) {
        clazzMapper.deleteClazzByIds(ids);
    }

    /**
     * 删除班级信息信息
     *
     * @param id 班级信息主键
     * @return 结果
     */
    @Override
    public void deleteClazzById(Long id) {
        clazzMapper.deleteClazzById(id);
    }

}
