package com.dtdhehe.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/8 17:17
 * @description
 **/
public class JwtUtils {

    /**
     * 签名私钥
     */
    private static final String KEY = "dtdhehe";
    /**
     * 签名的失效时间
     */
    private static final Long TTL = 60*60*1000L;

    /**
     * 设置认证token
     *      id:登录用户id
     *      subject：登录用户名
     *
     */
    public static String createJwt(String id, String name, Map<String,Object> map) {
        //1.设置失效时间
        //当前毫秒
        long now = System.currentTimeMillis();
        long exp = now + TTL;
        //2.创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(name)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, KEY);
        //3.根据map设置claims
        for(Map.Entry<String,Object> entry : map.entrySet()) {
            jwtBuilder.claim(entry.getKey(),entry.getValue());
        }
        jwtBuilder.setExpiration(new Date(exp));
        //4.创建token
        return jwtBuilder.compact();
    }


    /**
     * 解析token字符串获取clamis
     */
    public static Claims parseJwt(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

}
