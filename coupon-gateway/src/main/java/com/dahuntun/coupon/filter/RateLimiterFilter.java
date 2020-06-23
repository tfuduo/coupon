package com.dahuntun.coupon.filter;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 限流过滤器
 */
@Component
@SuppressWarnings("all")
public class RateLimiterFilter extends AbstractPreZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(RateLimiterFilter.class);

    /**
     * 限流器
     * 每秒可以获取到两个令牌
     */
    RateLimiter rateLimiter = RateLimiter.create(2.0);

    @Override
    protected Object cRun() {
        HttpServletRequest request = requestContext.getRequest();

        if (rateLimiter.tryAcquire()) {
            logger.info("get rate token success");
            return success();
        } else {
            logger.error("rate limit: {}", request.getRequestURI());
            return fail(HttpServletResponse.SC_PAYMENT_REQUIRED, "error: rate limit");
        }
    }

    @Override
    public int filterOrder() {
        return 2;
    }
}
