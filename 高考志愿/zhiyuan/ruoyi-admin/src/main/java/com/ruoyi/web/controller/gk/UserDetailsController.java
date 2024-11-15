package com.ruoyi.web.controller.gk;

import com.ruoyi.common.core.domain.RestBean;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gk.domain.UserInformation;
import com.ruoyi.gk.domain.vo.UserDetailsVo;
import com.ruoyi.gk.service.IUserDetailsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;

/**
 * 用户详情Controller
 * 
 * @author ruoyi
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/gk/user")
public class UserDetailsController extends BaseController
{
    @Autowired
    private IUserDetailsService userDetailsService;



    /**
     * 获取用户详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('gk:details:query')")
    @GetMapping(value = "/getInfo")
    public RestBean<UserDetailsVo> getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        return RestBean.Success(userDetailsService.selectUserDetailsByUserId(user.getUserId()));
    }



    /**
     * 修改用户详情
     */
    @PreAuthorize("@ss.hasPermi('gk:details:edit')")
    @Log(title = "用户详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public RestBean<Integer> edit(@RequestBody UserInformation userInformation)
    {
        return RestBean.Success(userDetailsService.updateUserDetails(userInformation));
    }

}
