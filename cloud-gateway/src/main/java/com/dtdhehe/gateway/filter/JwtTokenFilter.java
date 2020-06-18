package com.dtdhehe.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.dtdhehe.common.util.JwtUtils;
import com.dtdhehe.common.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/12 17:51
 * @description
 **/
//@Component
public class JwtTokenFilter implements GlobalFilter, Ordered {

    @Value("${jwt.skipAuthUrls}")
    private String[] skipAuthUrls;

    private static final String EXPIRE_ERROR_MESSAGE = "Allowed clock skew";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1. 获取请求
        ServerHttpRequest request = exchange.getRequest();
        //2. 则获取响应
        ServerHttpResponse response = exchange.getResponse();
        //3. 放行不需要过滤的请求
        for (String unAuthUrl : skipAuthUrls){
            if (request.getURI().getPath().contains(unAuthUrl)){
                return chain.filter(exchange);
            }
        }
        //4. 获取请求头
        HttpHeaders headers = request.getHeaders();
        //5. 请求头中获取令牌
        String token = headers.getFirst("Authorization");
        //6. 判断请求头中是否有令牌
        if (StringUtils.isEmpty(token)) {
            //7. 响应中放入返回的状态吗, 没有权限访问
            return authErro(response,"请登录");
        }
        //9. 如果请求头中有令牌则解析令牌
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            //10. 解析jwt令牌出错, 说明令牌过期或者伪造等不合法情况出现
            e.printStackTrace();
            //11. 返回
            if(e.getMessage().contains(EXPIRE_ERROR_MESSAGE)){
                return authErro(response,"认证过期");
            }
            return authErro(response,"认证失败");
        }
        //12. 放行
        return chain.filter(exchange);
    }

    private Mono<Void> authErro(ServerHttpResponse resp, String mess) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String returnStr = JSON.toJSONString(ResultVO.unauth(mess));
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
