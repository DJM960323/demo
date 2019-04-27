package com.example.demo.excption;

/**
 * Created by 邓建明 on 2019-4-27.
 */
public enum ResponseCode {

    成功(200,"成功"),

    用户名或密码错误(601,"用户名或密码错误"),

    手机号已被注册(801,"手机号已被注册"),

    用户名已存在(808,"用户名已存在");

    private int code;
    private String msg;

    ResponseCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
