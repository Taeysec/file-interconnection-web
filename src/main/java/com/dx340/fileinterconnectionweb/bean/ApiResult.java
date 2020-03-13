package com.dx340.fileinterconnectionweb.bean;

/**
 * @author Zsk-d
 */
public class ApiResult {
    private Integer status = null;
    private String message = null;
    private Object res = null;

    public ApiResult() {
    }

    public ApiResult(Integer status, String message, Object res) {
        this.status = status;
        this.message = message;
        this.res = res;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRes() {
        return res;
    }

    public void setRes(Object res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", res=" + res.toString() +
                '}';
    }
}
