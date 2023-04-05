package com.taritari.blog.utils;

import cn.hutool.core.convert.Convert;
import com.taritari.blog.config.YmlConfig;
import com.taritari.blog.entity.dto.UserTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * 生成JWT令牌
 * @author taritari
 * @date 2023-4-2
 */
public class JwtUtils {
    private YmlConfig ymlConfig = new YmlConfig();
    private Map<String, Object> datasource = ymlConfig.getApplication("jwt");
    private String secretKeyString =datasource.get("secret").toString(); // 替换为您的密钥字符串
    private Key secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    private int expiration = Convert.toInt(datasource.get("expiration"));


    /**
     * 生成JWT令牌
     * @param username 用户名
     * @return 生成的JWT令牌
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expiration * 1000);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(secretKey)
                .compact();
    }

   /**
     * 解析JWT令牌
     * @param token JWT令牌
     * @return 包含用户名、过期时间和签发时间的用户令牌数据传输对象
     */
    public UserTokenDto JwtDecoder(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        Date expiration = claims.getExpiration();
        Date issuedAt = claims.getIssuedAt();
        UserTokenDto userTokenDto = new UserTokenDto(username,expiration,issuedAt);
        return userTokenDto;
    }

}
