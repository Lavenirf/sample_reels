package org.example.labsystem.service.impl;

import jakarta.annotation.Resource;
import org.example.labsystem.exception.ReadableException;
import org.example.labsystem.mapper.RoleMapper;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.entity.Role;
import org.example.labsystem.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper mapper;
    @Override
    public List<Role> getRoleList() {
        return mapper.getRoleList();
    }

    @Override
    public void updateRole(Role role) {
        if (!mapper.updateRole(role)) throw new ReadableException(CodeState.UPDATE_ERROR);

    }

    @Override
    public void deleteRole(Integer id) {
        if (!mapper.deleteRole(id)) throw new ReadableException(CodeState.DELETE_ERROR);
    }
}
