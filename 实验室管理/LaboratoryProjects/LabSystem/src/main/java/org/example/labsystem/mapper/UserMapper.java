package org.example.labsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.labsystem.pojo.entity.User;

import java.util.List;
@Mapper
public interface UserMapper {
    User login(String username,String password);
    List<User> getUserList(User user);
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(Integer id);
}
