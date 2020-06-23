package com.dahuntun.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关应用启动入口
 * 1. @EnableZuulProxy 标识当前应用是 Zuul Server
 * 2. @SpringCloudApplication 组合了 Spring Boot 应用 + 服务发现 + 熔断
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }
}
