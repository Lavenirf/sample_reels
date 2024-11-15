package org.example.labsystem.service;

import org.example.labsystem.pojo.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoleList();

    void updateRole(Role role);

    void deleteRole(Integer id);
}
