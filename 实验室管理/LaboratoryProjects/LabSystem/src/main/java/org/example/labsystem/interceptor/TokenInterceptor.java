package org.example.labsystem.interceptor;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.labsystem.exception.ReadableException;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.common.RedisFinal;
import org.example.labsystem.pojo.entity.User;
import org.example.labsystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private SpringUtil springUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token =request.getHeader("Authorization");
        if (StrUtil.isEmpty(token)) throw new ReadableException(CodeState.LOGIN_NOT);
        String s =(String) redisTemplate.opsForValue().get(RedisFinal.redisCode+token);
        User user = JSON.parseObject(s, User.class);
        if (ObjectUtil.isNull(user)) throw new ReadableException(CodeState.JWT_ERROR);
        redisTemplate.expire(RedisFinal.redisCode+token,30, TimeUnit.MINUTES);
        return true;
    }
}
