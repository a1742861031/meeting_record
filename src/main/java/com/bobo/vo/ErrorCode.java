package com.bobo.vo;

/**
 * @Description 错误状态码
 * @Date 2022/1/12 13:26
 * @Created by bobo
 */
public enum ErrorCode {
    Network_error("9999", "服务器正在升级中，请稍后再试"),
    ;

    private String code;
    private String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
