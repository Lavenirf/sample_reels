package org.example.labsystem.controller;

import jakarta.annotation.Resource;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.common.Result;
import org.example.labsystem.pojo.entity.Role;
import org.example.labsystem.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Resource
    private RoleService service;
    @GetMapping("/list")
    public Result<List<Role>> getRoleList(){
        List<Role> roleList = service.getRoleList();
        return Result.Success(CodeState.SUCCESS,roleList);
    }
    @PutMapping(value = "/{id}")
    public Result<?> updateRole(@PathVariable Integer id,@RequestBody Role role){
        role.setId(id);
        service.updateRole(role);
        return Result.Success(CodeState.SUCCESS);
    }
    @DeleteMapping(value = "/{id}")
    public Result<?> deleteRole(@PathVariable Integer id){
        service.deleteRole(id);
        return Result.Success(CodeState.SUCCESS);
    }
}
