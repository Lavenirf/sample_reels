package org.example.labsystem.service.impl;

import jakarta.annotation.Resource;
import org.example.labsystem.exception.ReadableException;
import org.example.labsystem.mapper.LabMapper;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.entity.Lab;
import org.example.labsystem.service.LabService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabServiceImpl implements LabService {
    @Resource
    private LabMapper mapper;
    @Override
    public List<Lab> getLabList() {
        return mapper.getLabs();
    }

    @Override
    public void addLab(Lab lab) {
        boolean b = mapper.insertLab(lab);
        if (!b) throw new ReadableException(CodeState.INSERT_ERROR);
    }

    @Override
    public void updateLab(Lab lab) {
        boolean b = mapper.updateLab(lab);
        if (!b) throw new ReadableException(CodeState.UPDATE_ERROR);
    }

    @Override
    public void deleteLab(Integer id) {
        boolean b = mapper.deleteLab(id);
        if (!b) throw new ReadableException(CodeState.DELETE_ERROR);
    }
}
