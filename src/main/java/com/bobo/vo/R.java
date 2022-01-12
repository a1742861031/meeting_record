package com.bobo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description 自定义返回结果
 * @Date 2022/1/12 13:23
 * @Created by bobo
 */
@Getter
@Setter
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class R<T> implements Serializable {

    private static final long serialVersionUID = -4505655308965878999L;

    //请求成功返回码为：0000
    private static final String successCode = "200";
    //返回数据
    private T data;
    //返回码
    private String code;
    //返回描述
    private String msg;

    public R() {
        this.code = successCode;
        this.msg = "请求成功";
    }

    public R(String code, String msg) {
        this();
        this.code = code;
        this.msg = msg;
    }

    public R(String code, String msg, T data) {
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public R(T data) {
        this();
        this.data = data;
    }
}
