package org.example.labsystem.service;

import org.example.labsystem.pojo.entity.Right;

import java.util.List;

public interface RightService {
    List<Right> getRightList();

    void updateRightList(Right right);

    void deleteRight(int id);
}
