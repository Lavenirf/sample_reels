package com.core.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.entity.Student;
import com.core.entity.Teacher;
import com.core.entity.Token;
import com.core.entity.User;
import com.core.exception.ServiceException;
import com.core.mapper.StudentMapper;
import com.core.mapper.TeacherMapper;
import com.core.mapper.UserMapper;
import com.core.service.StudentService;
import com.core.service.TeacherService;
import com.core.service.UserService;
import com.core.utils.redis.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 账户表信息业务层处理
 *
 * @author zph
 * @date 2023-07-14 19:35:34
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private TeacherMapper teacherMapper;


    /**
     * 账号登录
     */
    @Override
    public Token login(String username, String password) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username).eq(User::getPassword, password);
        User user = userMapper.selectOne(lambdaQueryWrapper);

        if (user == null) {
            throw new ServiceException("账号或密码错误");
        }

        Token tokenBean = new Token();
        String token = IdUtil.fastSimpleUUID();
        if ("学员".equals(user.getType())) {
            LambdaQueryWrapper<Student> studentQueryWrapper = new LambdaQueryWrapper<>();
            studentQueryWrapper.eq(Student::getUserId, user.getId());
            Student student = studentMapper.selectOne(studentQueryWrapper);
            tokenBean.setStudentId(student.getId());
        } else {
            LambdaQueryWrapper<Teacher> teacherQueryWrapper = new LambdaQueryWrapper<>();
            teacherQueryWrapper.eq(Teacher::getUserId, user.getId());
            Teacher teacher = teacherMapper.selectOne(teacherQueryWrapper);
            tokenBean.setTeacherId(teacher.getId());
        }
        tokenBean.setUserId(user.getId());
        tokenBean.setToken(token);
        tokenBean.setType(user.getType());
        redisUtil.set(token, user);
        return tokenBean;
    }

    /**
     * 查询账户表信息
     *
     * @param id 账户表信息主键
     * @return 账户表信息
     */
    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    /**
     * 分页查询账户表信息列表
     *
     * @param user 账户表信息
     * @return 账户表信息
     */
    @Override
    public IPage<User> selectUserPage(User user) {
        IPage<User> iPage = new Page<>(user.getPage(), user.getPageSize());
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>(user);
        lambdaQueryWrapper.orderByDesc(User::getCreateTime);
        return userMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    /**
     * 查询账户表信息列表
     *
     * @param user 账户表信息
     * @return 账户表信息
     */
    @Override
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增账户表信息
     *
     * @param user 账户表信息
     * @return 结果
     */
    @Override
    public void insertUser(User user) {
        user.setCreateTime(new Date());
        userMapper.insertUser(user);
    }

    /**
     * 修改账户表信息
     *
     * @param user 账户表信息
     * @return 结果
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 批量删除账户表信息
     *
     * @param ids 需要删除的账户表信息主键
     * @return 结果
     */
    @Override
    public void deleteUserByIds(Long[] ids) {
        userMapper.deleteUserByIds(ids);
    }

    /**
     * 删除账户表信息信息
     *
     * @param id 账户表信息主键
     * @return 结果
     */
    @Override
    public void deleteUserById(Long id) {
        userMapper.deleteUserById(id);
    }

}
