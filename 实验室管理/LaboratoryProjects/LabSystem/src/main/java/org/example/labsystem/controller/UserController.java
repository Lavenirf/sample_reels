package org.example.labsystem.controller;

import jakarta.annotation.Resource;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.common.Result;
import org.example.labsystem.pojo.entity.User;
import org.example.labsystem.pojo.vo.UserVo;
import org.example.labsystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Resource
    private UserService service;


    @GetMapping("/list")
    public Result<List<User>> getUserList(){
        List<User> userList = service.getUserList(new User());
        return Result.Success(CodeState.SUCCESS,userList);
    }
    @PostMapping("/login")
    public Result<UserVo> Login(@RequestBody User user){
        UserVo vo = new UserVo();
        vo.setUser(user);
        UserVo userList = service.login(vo);
        return Result.Success(CodeState.SUCCESS,userList);
    }
    @PutMapping(value = "/{id}")
    public Result<?> updateUser(@PathVariable Integer id, @RequestBody User user){
        user.setId(id);
        service.updateUser(user);
        return Result.Success(CodeState.SUCCESS);
    }
    @PostMapping
    public Result<?> addUser(@RequestBody User user){
        service.insertUser(user);
        return Result.Success(CodeState.SUCCESS);
    }
    @DeleteMapping(value = "/{id}")
    public Result<?> deleteMapper(@PathVariable Integer id){
        service.deleteUser(id);
        return Result.Success(CodeState.SUCCESS);
    }
    @GetMapping("/logout")
    public Result<?> logout(@RequestHeader(name = "Authorization") String token){
        service.logout(token);
        return Result.Success(CodeState.SUCCESS);
    }
}
