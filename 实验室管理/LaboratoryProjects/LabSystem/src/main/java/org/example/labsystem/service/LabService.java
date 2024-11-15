package org.example.labsystem.service;

import org.example.labsystem.pojo.entity.Lab;

import java.util.List;

public interface LabService {
    List<Lab> getLabList();

    void addLab(Lab lab);

    void updateLab(Lab lab);

    void deleteLab(Integer id);
}
