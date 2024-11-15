package com.core.interceptor.config;

import com.core.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    private static final String[] defaultExcludes = new String[]{"/api/user/login", "/api/user/logout", "/api/user/register",
            "/static/**", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/*", "/csrf", "/doc.html", "/favicon.ico"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(defaultExcludes);
    }

    @Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }
}
