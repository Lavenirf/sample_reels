package org.example.labsystem.utils;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@ConfigurationProperties(prefix = "config.jwt",ignoreInvalidFields = true)
@Component
@Data
public class JwtUtils {
    /**
     * 过期时间一天，
     * 1000 * 24 * 60 * 60 一天
     * 1000 * 60 * 60 一小时
     * 120 * 60 * 1000 120分钟
     */
    private long EXPIRE_TIME;
    /**
     * token私钥
     */
    private String TOKEN_SECRET;
    private String HEADER;

    /**
     * 生成token
     *
     * @return 加密的token
     */
    public String createToken(String data) {
        Date nowData = new Date();
        Date expireDate = new Date(nowData.getTime()+EXPIRE_TIME*1000);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(data)
                .setIssuedAt(nowData)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,TOKEN_SECRET)
                .compact();
    }
    /**
    * 解析token
    * */
    public Claims getTokenClaim(String token){
        try {
            return Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
    public String getData(String token){
        return getTokenClaim(token).getSubject();
    }
}