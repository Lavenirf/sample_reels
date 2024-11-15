package org.example.labsystem.controller;

import jakarta.annotation.Resource;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.common.Result;
import org.example.labsystem.pojo.entity.Right;
import org.example.labsystem.service.RightService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rights")
public class RightController {
    @Resource
    private RightService service;
    @GetMapping("/list")
    public Result<List<Right>> getRightList(){
        return Result.Success(CodeState.SUCCESS,service.getRightList());
    }
    @PutMapping(value = "/{id}")
    public Result<?> updateRightList(@PathVariable Integer id, @RequestBody Right right){
        right.setId(id);
        service.updateRightList(right);
        return Result.Success(CodeState.SUCCESS);
    }
    @DeleteMapping(value = "/{id}")
    public Result<?> deleteRight(@PathVariable int id){
        service.deleteRight(id);
        return Result.Success(CodeState.SUCCESS);
    }
}
