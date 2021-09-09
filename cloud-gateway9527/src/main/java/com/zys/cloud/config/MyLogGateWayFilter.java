package com.zys.cloud.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * 自定义过滤器
 */
//@Component
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    private static Logger log = LoggerFactory.getLogger(MyLogGateWayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("token");
        //非法用户直接拦截
        if (token == null) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 401);
            map.put("msg", "未授权，无法访问");
            byte[] errs = JSON.toJSONBytes(map);
            DataBuffer buffer = response.bufferFactory().wrap(errs);
            return response.writeWith(Flux.just(buffer));
        }
        return chain.filter(exchange);
    }

    //order是过滤器的执行顺序，数字越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}