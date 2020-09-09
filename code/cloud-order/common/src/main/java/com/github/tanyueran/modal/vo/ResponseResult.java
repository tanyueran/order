package com.github.tanyueran.modal.vo;

import java.io.Serializable;

/**
 * 微服务的所有接口，响应数据的结构
 * code: 按照http code走
 */
public class ResponseResult implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public static Integer success_code = 200;

    public static ResponseResult success(Object data) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(success_code);
        responseResult.setMsg("请求成功");
        responseResult.setData(data);
        return responseResult;
    }

    public static ResponseResult success(Object data, String msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(success_code);
        responseResult.setMsg("请求成功");
        responseResult.setData(data);
        responseResult.setMsg(msg);
        return responseResult;
    }

    public ResponseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
