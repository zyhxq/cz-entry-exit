package com.lx.springboot.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理认证失败异常
     *
     * @param e
     * @param model
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public String authenticationExceptionProcessing(AuthenticationException e, Model model) {
        String msg = null;
        if (e instanceof DisabledAccountException) {
            msg = "账户异常";
        } else if (e instanceof IncorrectCredentialsException) {
            msg = "账户/密码错误";
        } else {
            msg = "系统发生异常";
        }
        model.addAttribute("errorMsg", msg);
        return "forward:/user/login";
    }

    /**
     * 处理授权失败异常
     *
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public String authorizedExceptionProcessing() {
        return "error/unauthorizedException";
    }
}
