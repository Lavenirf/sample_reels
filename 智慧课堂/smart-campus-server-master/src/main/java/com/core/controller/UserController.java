package com.core.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.core.entity.Token;
import com.core.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.core.entity.User;
import com.core.service.UserService;

/**
 * 账户表信息控制层
 *
 * @author zph
 * @date 2023-07-14 19:35:34
 */
@Api(tags = "账户表信息控制层")
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 账号登录
     */
    @GetMapping("/login")
    public Token login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    /**
     * 获取账户表信息详细信息
     */
    @ApiOperation(value = "获取账户表信息详细信息", notes = "获取账户表信息详细信息", response = User.class)
    @GetMapping(value = "/{id}")
    public User getInfo(@PathVariable("id") Long id) {
        return userService.selectUserById(id);
    }

    /**
     * 分页查询账户表信息列表
     */
    @ApiOperation(value = "分页查询账户表信息列表", notes = "分页查询账户表信息列表", response = User.class)
    @GetMapping("/page")
    public IPage<User> page(User user) {
        IPage<User> page = userService.selectUserPage(user);
        return page;
    }

    /**
     * 查询账户表信息列表
     */
    @ApiOperation(value = "查询账户表信息列表", notes = "查询账户表信息列表", response = User.class)
    @GetMapping("/list")
    public List<User> list(User user) {
        List<User> list = userService.selectUserList(user);
        return list;
    }

    /**
     * 新增账户表信息
     */
    @ApiOperation(value = "新增账户表信息", notes = "新增账户表信息")
    @PostMapping
    public void add(@RequestBody User user) {
        userService.insertUser(user);
    }

    /**
     * 修改账户表信息
     */
    @ApiOperation(value = "修改账户表信息", notes = "修改账户表信息")
    @PutMapping
    public void edit(@RequestBody User user) {
        userService.updateUser(user);
    }

    /**
     * 删除账户表信息
     */
    @ApiOperation(value = "删除账户表信息", notes = "删除账户表信息")
    @DeleteMapping("/{ids}")
    public void remove(@PathVariable Long[] ids) {
        userService.deleteUserByIds(ids);
    }

}
