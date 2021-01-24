package com.jin.eledger.pojo;

import java.io.Serializable;

public class ResultMsg implements Serializable {
    private static final long serialVersionUID = 5368610734830950791L;
    private String code="0";
    private String msg="";

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
