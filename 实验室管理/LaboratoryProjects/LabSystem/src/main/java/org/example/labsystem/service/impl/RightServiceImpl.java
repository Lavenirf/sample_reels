package org.example.labsystem.service.impl;

import jakarta.annotation.Resource;
import org.example.labsystem.exception.ReadableException;
import org.example.labsystem.mapper.RightMapper;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.entity.Right;
import org.example.labsystem.service.RightService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RightServiceImpl implements RightService {
    @Resource
    private RightMapper mapper;
    @Override
    public List<Right> getRightList() {
         return mapper.getRightList();
    }

    @Override
    public void updateRightList(Right right) {
       if (mapper.updateRightList(right)) throw new ReadableException(CodeState.UPDATE_ERROR);
    }

    @Override
    public void deleteRight(int id) {
       if (mapper.deleteRight(id)) throw new ReadableException(CodeState.DELETE_ERROR);
    }
}
