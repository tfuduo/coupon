package com.dahuntun.coupon.filter;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验请求中传递的 Token
 */
@Component
public class TokenFilter extends AbstractPreZuulFilter{
    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    protected Object cRun() {
        HttpServletRequest request = requestContext.getRequest();
        logger.info(String.format("%s request to %s",
                request.getMethod(), request.getRequestURL().toString()));
        Object token = request.getParameter("token");
        if (null == token) {
            logger.error("error: token is empty");
            return fail(HttpServletResponse.SC_UNAUTHORIZED, "error: token is empty");
        }
        return null;
    }

    //返回的数字越小越先执行
    @Override
    public int filterOrder() {
        return 1;
    }
}
