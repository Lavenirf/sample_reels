package com.core.controller;

import com.core.entity.User;
import com.core.utils.redis.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    protected HttpServletRequest httpServletRequest;

    @Autowired
    private RedisUtil redisUtil;

    protected String getToken() {
        return httpServletRequest.getHeader("X-Token");
    }

    protected User getUserByToken() {
        String token = httpServletRequest.getHeader("X-Token");
        if (StringUtils.isNotBlank(token)) {
            return (User) redisUtil.get(token);
        }
        return null;
    }

}
