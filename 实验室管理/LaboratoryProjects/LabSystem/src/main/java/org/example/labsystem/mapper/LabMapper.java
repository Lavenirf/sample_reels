package org.example.labsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.labsystem.pojo.entity.Lab;

import java.util.List;

@Mapper
public interface LabMapper {
    List<Lab> getLabs();
    boolean updateLab(Lab lab);
    boolean insertLab(Lab lab);
    boolean deleteLab(Integer id);
}
