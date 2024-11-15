package org.example.labsystem.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.example.labsystem.pojo.entity.Role;

import java.util.List;
@Mapper
public interface RoleMapper {
    List<Role> getRoleList();

    boolean updateRole(Role role);

    @Delete("delete from roles where id=#{id}")
    boolean deleteRole(Integer id);
}
