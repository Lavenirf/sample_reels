package com.ruoyi.gk.mapper;

import com.ruoyi.gk.domain.UserDetails;
import com.ruoyi.gk.domain.UserInformation;

/**
 * 用户详情Mapper接口
 *
 * @author ruoyi
 * @date 2024-06-26
 */
public interface UserDetailsMapper
{
    /**
     * 查询用户详情
     *
     * @param userId 用户详情主键
     * @return 用户详情
     */
    public UserInformation selectUserDetailsByUserId(Long userId);

    /**
     * 修改用户详情
     *
     * @param userDetails 用户详情
     * @return 结果
     */
    public int updateUserDetails(UserInformation userDetails);

    /**
     * 新增用户详情
     *
     * @param userDetails 用户详情
     * @return 结果
     */
    public int insertUserDetails(UserDetails userDetails);

}
