package com.dahuntun.coupon.advice;

import com.dahuntun.coupon.annotation.IgnoreResponseAdvice;
import com.dahuntun.coupon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <h1>统一响应</h1>
 * @author HuTao
 * @date 2020/7/8 14:29
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * <h1>判断是否需要对响应进行处理</h1>
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class aClass) {

        //如果当前方法所在类标识了 @IgnoreResponseAdvice 注解，不需要处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        //如果当前方法标识了 @IgnoreResponseAdvice 注解，不需要处理
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        //对响应进行处理，执行 beforeBodyWrite(...)
        return true;
    }

    /**
     * <h1>响应返回之前的处理</h1>
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //定义最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(0, "");

        //如果对象o为 null ，response 不需要设置data
        if (null == o) {
            return response;
        }

        //如果 o 已经是 CommonResponse ， 不需要再次处理
        if (o instanceof CommonResponse) {
            response = (CommonResponse<Object>) o;
            return response;
        }

        response.setData(o);
        return response;
    }
}
