package com.bobo.vo;

/**
 * @Description 自定义异常返回
 * @Date 2022/1/12 13:28
 * @Created by bobo
 */

public class MyException extends RuntimeException {

    private static final long serialVersionUID = -6370612186038915645L;

    private final R r;

    public MyException(R r) {
        this.r = r;
    }

    public R getResponse() {
        return r;
    }
}

