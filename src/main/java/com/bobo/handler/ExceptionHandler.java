package com.bobo.handler;

import com.bobo.vo.ErrorCode;
import com.bobo.vo.MyException;
import com.bobo.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 异常处理
 * @Date 2022/1/12 13:32
 * @Created by bobo
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(MyException.class)
    @ResponseBody
    public R handleStudentException(HttpServletRequest request, MyException ex) {
        R r;
        log.error("StudentException code:{},msg:{}", ex.getResponse().getCode(), ex.getResponse().getMsg());
        r = new R(ex.getResponse().getCode(), ex.getResponse().getMsg());
        return r;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public R handleException(HttpServletRequest request, Exception ex) {
        R r;
        log.error("exception error:{}", ex);
        r = new R(ErrorCode.Network_error.getCode(),
                ErrorCode.Network_error.getMsg());
        return r;
    }
}
