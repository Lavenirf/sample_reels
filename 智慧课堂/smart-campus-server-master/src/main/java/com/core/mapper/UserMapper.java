package com.core.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.core.entity.User;

/**
 * 账户表信息数据库接口
 *
 * @author zph
 * @date 2023-07-14 19:35:34
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询账户表信息
     *
     * @param id 账户表信息主键
     * @return 账户表信息
     */
    User selectUserById(Long id);

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
     * 删除账户表信息
     *
     * @param id 账户表信息主键
     * @return 结果
     */
    void deleteUserById(Long id);

    /**
     * 批量删除账户表信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    void deleteUserByIds(Long[] ids);
}
