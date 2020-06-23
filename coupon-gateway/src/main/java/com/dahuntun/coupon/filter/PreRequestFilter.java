package com.dahuntun.coupon.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 在过滤器中存储客户端发送请求的时间戳
 */
@Component
public class PreRequestFilter extends AbstractPreZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(PreRequestFilter.class);

    @Override
    protected Object cRun() {
        requestContext.set("startTime", System.currentTimeMillis());
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
