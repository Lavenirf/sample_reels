package com.core.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.core.entity.Token;
import com.core.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 账户表信息业务层接口
 *
 * @author zph
 * @date 2023-07-14 19:35:34
 */
public interface UserService {
    /**
     * 账号登录
     *
     */
    Token login(String username, String password);

    /**
     * 查询账户表信息
     *
     * @param id 账户表信息主键
     * @return 账户表信息
     */
     User selectUserById(Long id);

    /**
     * 分页查询账户表信息列表
     *
     * @param user 账户表信息
     * @return 账户表信息集合
     */
    IPage<User> selectUserPage(User user);

    /**
     * 查询账户表信息列表
     *
     * @param user 账户表信息
     * @return 账户表信息集合
     */
    List<User> selectUserList(User user);

    /**
     * 新增账户表信息
     *
     * @param user 账户表信息
     * @return 结果
     */
    void insertUser(User user);

    /**
     * 修改账户表信息
     *
     * @param user 账户表信息
     * @return 结果
     */
    void updateUser(User user);

    /**
     * 批量删除账户表信息
     *
     * @param ids 需要删除的账户表信息主键集合
     * @return 结果
     */
    void deleteUserByIds(Long[] ids);

    /**
     * 删除账户表信息信息
     *
     * @param id 账户表信息主键
     * @return 结果
     */
    void deleteUserById(Long id);


}
