package com.ruoyi.gk.service;

import com.ruoyi.gk.domain.UserInformation;
import com.ruoyi.gk.domain.vo.UserDetailsVo;



/**
 * 用户详情Service接口
 * 
 * @author ruoyi
 * @date 2024-06-26
 */
public interface IUserDetailsService 
{
    /**
     * 查询用户详情
     * 
     * @param userId 用户详情主键
     * @return 用户详情
     */
    public UserDetailsVo selectUserDetailsByUserId(Long userId);


    /**
     * 修改用户详情
     * 
     * @param userInformation 用户详情
     * @return 结果
     */
    public int updateUserDetails(UserInformation userInformation);

}
