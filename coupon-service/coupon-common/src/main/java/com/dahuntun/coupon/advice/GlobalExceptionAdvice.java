package com.dahuntun.coupon.advice;

import com.dahuntun.coupon.exception.CouponException;
import com.dahuntun.coupon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>全局异常处理</h1>
 * @author HuTao
 * @date 2020/7/9 14:15
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * <h2>对 CouponException 进行统一处理</h2>
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = CouponException.class)
    public CommonResponse<String> handlerCouponException(HttpServletRequest request
            , CouponException exception) {
        CommonResponse<String> response = new CommonResponse<>(-1, "Business error");
        response.setData(exception.getMessage());
        return response;
    }
}
