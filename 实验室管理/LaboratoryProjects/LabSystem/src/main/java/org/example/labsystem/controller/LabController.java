package org.example.labsystem.controller;

import jakarta.annotation.Resource;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.common.Result;
import org.example.labsystem.pojo.entity.Lab;
import org.example.labsystem.service.LabService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labs")
public class LabController {
    @Resource
    private LabService service;
    @GetMapping("list")
    public Result<List<Lab>> getLabs(){
       List<Lab> list =  service.getLabList();
        return Result.Success(CodeState.SUCCESS,list);
    }
    @PostMapping
    public Result<?> addLab(@RequestBody Lab lab){
        service.addLab(lab);
        return Result.Success(CodeState.SUCCESS);
    }
    @PutMapping(value = "/{id}")
    public Result<?> updateLab(@PathVariable Integer id,@RequestBody Lab lab){
        lab.setId(id);
        service.updateLab(lab);
        return Result.Success(CodeState.SUCCESS);
    }
    @DeleteMapping(value = "/{id}")
    public Result<?> deleteLab(@PathVariable Integer id){
        service.deleteLab(id);
        return Result.Success(CodeState.SUCCESS);
    }
}
