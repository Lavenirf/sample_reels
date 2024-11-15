package org.example.labsystem.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import org.example.labsystem.exception.ReadableException;
import org.example.labsystem.mapper.UserMapper;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.common.RedisFinal;
import org.example.labsystem.pojo.entity.User;
import org.example.labsystem.pojo.vo.UserVo;
import org.example.labsystem.service.UserService;
import org.example.labsystem.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper mapper;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public List<User> getUserList(User user) {
        return mapper.getUserList(user);
    }

    @Override
    public UserVo login(UserVo userVo) {
        String username = userVo.getUser().getUsername();
        String password = userVo.getUser().getPassword();
        User user = mapper.login(username, password);
        return Optional.ofNullable(user)
                .map(u->{
                    String token = jwtUtils.createToken(user.getUsername());
                    redisTemplate.opsForValue().set(RedisFinal.redisCode+token, JSON.toJSONString(user),7, TimeUnit.DAYS);
                    return new UserVo(token, user);
                })
                .orElseThrow(()-> new ReadableException(CodeState.LOGINERROR));
    }

    @Override
    public void updateUser(User user) {
        System.out.println(user.toString());
        boolean b = mapper.updateUser(user);
        if (!b) throw new ReadableException(CodeState.UPDATE_ERROR);
    }

    @Override
    public void insertUser(User user) {
        boolean b = mapper.insertUser(user);
        if (!b) throw new ReadableException(CodeState.INSERT_ERROR);
    }

    @Override
    public void deleteUser(Integer id) {
        boolean b = mapper.deleteUser(id);
        if (!b) throw new ReadableException(CodeState.DELETE_ERROR);
    }

    @Override
    public void logout(String token) {
        Boolean delete = redisTemplate.delete(RedisFinal.redisCode + token);
    }

}
