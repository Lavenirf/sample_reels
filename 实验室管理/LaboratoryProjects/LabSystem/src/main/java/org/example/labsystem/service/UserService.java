package org.example.labsystem.service;

import org.example.labsystem.pojo.entity.User;
import org.example.labsystem.pojo.vo.UserVo;

import java.util.List;

public interface UserService {
    List<User> getUserList(User user);

    UserVo login(UserVo user);

    void updateUser(User user);

    void insertUser(User user);

    void deleteUser(Integer id);

    void logout(String token);
}
