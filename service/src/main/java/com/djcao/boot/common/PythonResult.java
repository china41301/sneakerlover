package com.djcao.boot.common;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-11
 */
public class PythonResult<T> {
    private String code;
    private String error_msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
