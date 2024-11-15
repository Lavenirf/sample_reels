package com.core.service.impl;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.core.entity.Clazz;
import com.core.entity.Student;
import com.core.service.ClazzService;
import org.springframework.stereotype.Service;
import com.core.mapper.TeacherClazzMapper;
import com.core.entity.TeacherClazz;
import com.core.service.TeacherClazzService;

/**
 * 教师班级信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:39
 */
@Service
public class TeacherClazzServiceImpl implements TeacherClazzService {

    @Resource
    private TeacherClazzMapper teacherClazzMapper;

    @Resource
    private ClazzService clazzService;

    /**
     * 通过教师主键获取班级列表
     */
    @Override
    public List<Clazz> selectClazzByTeacherId(Long teacherId) {
        LambdaQueryWrapper<TeacherClazz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TeacherClazz::getTeacherId, teacherId);
        lambdaQueryWrapper.orderByDesc(TeacherClazz::getCreateTime);
        List<TeacherClazz> teacherClazzes = teacherClazzMapper.selectList(lambdaQueryWrapper);
        List<Clazz> clazzes = new ArrayList<>();
        for (TeacherClazz teacherClazz : teacherClazzes) {
            Clazz clazz = clazzService.selectClazzById(teacherClazz.getClazzId());
            clazzes.add(clazz);
        }

        return clazzes;
    }

    /**
     * 查询教师班级信息
     *
     * @param id 教师班级信息主键
     * @return 教师班级信息
     */
    @Override
    public TeacherClazz selectTeacherClazzById(Long id) {
        return teacherClazzMapper.selectTeacherClazzById(id);
    }

    /**
     * 分页查询教师班级信息列表
     *
     * @param teacherClazz 教师班级信息
     * @return 教师班级信息
     */
    @Override
    public IPage<TeacherClazz> selectTeacherClazzPage(TeacherClazz teacherClazz) {
        IPage<TeacherClazz> iPage = new Page<>(teacherClazz.getPage(), teacherClazz.getPageSize());
        LambdaQueryWrapper<TeacherClazz> lambdaQueryWrapper = new LambdaQueryWrapper<>(teacherClazz);
        lambdaQueryWrapper.orderByDesc(TeacherClazz::getCreateTime);
        return teacherClazzMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询教师班级信息列表
     *
     * @param teacherClazz 教师班级信息
     * @return 教师班级信息
     */
    @Override
    public List<TeacherClazz> selectTeacherClazzList(TeacherClazz teacherClazz) {
        return teacherClazzMapper.selectTeacherClazzList(teacherClazz);
    }

    /**
     * 新增教师班级信息
     *
     * @param teacherClazz 教师班级信息
     * @return 结果
     */
    @Override
    public void insertTeacherClazz(TeacherClazz teacherClazz) {
        teacherClazz.setCreateTime(new Date());
        teacherClazzMapper.insertTeacherClazz(teacherClazz);
    }

    /**
     * 修改教师班级信息
     *
     * @param teacherClazz 教师班级信息
     * @return 结果
     */
    @Override
    public void updateTeacherClazz(TeacherClazz teacherClazz) {
        teacherClazzMapper.updateTeacherClazz(teacherClazz);
    }

    /**
     * 批量删除教师班级信息
     *
     * @param ids 需要删除的教师班级信息主键
     * @return 结果
     */
    @Override
    public void deleteTeacherClazzByIds(Long[] ids) {
        teacherClazzMapper.deleteTeacherClazzByIds(ids);
    }

    /**
     * 删除教师班级信息信息
     *
     * @param id 教师班级信息主键
     * @return 结果
     */
    @Override
    public void deleteTeacherClazzById(Long id) {
        teacherClazzMapper.deleteTeacherClazzById(id);
    }

}
