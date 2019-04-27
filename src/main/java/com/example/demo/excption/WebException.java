package com.example.demo.excption;

/**
 * Created by 邓建明 on 2019-4-27.
 */
public class WebException extends RuntimeException{
    private int code;
    private String msg;

    public WebException(int code,String msg){
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
