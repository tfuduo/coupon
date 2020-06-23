package com.dahuntun.coupon.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessLogFilter extends AbstractPostZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

    @Override
    protected Object cRun() {
        HttpServletRequest request = requestContext.getRequest();

        // 从 PreRequestFilter 中获取设置的请求时间戳
        Long startTime = (Long) requestContext.get("startTime");
        String uri = request.getRequestURI();
        long duration = System.currentTimeMillis() - startTime;

        // 打印所有从网关通过的请求的日志记录：uri + duration
        logger.info("uri: {}, duration: {}", uri, duration);

        return success();
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }
}
