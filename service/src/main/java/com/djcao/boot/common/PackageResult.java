package com.djcao.boot.common;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-03
 */
public class PackageResult<T> {


    private boolean success;

    private int code;

    private String message;

    private T result;

    private int pageNo = 0;

    private int pageSize = 30;

    private static PackageResult create(boolean success,int code){
        return create(success, StringUtils.EMPTY,code);
    }

    private static PackageResult create(boolean success, String message,int code){
        return new PackageResult().setSuccess(success).setMessage(message).setCode(code);
    }

    public static PackageResult success(){
        return create(Boolean.TRUE,CodeDef.SUCCESS);
    }

    public static PackageResult error(String message){
        return create(Boolean.FALSE,message,CodeDef.ERROR);
    }

    public boolean isSuccess() {
        return success;
    }

    public PackageResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public PackageResult setMessage(String message) {
        this.message = message;
        return this;
    }


    public int getPageNo() {
        return pageNo;
    }

    public PackageResult setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PackageResult setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getCode() {
        return code;
    }

    public PackageResult setCode(int code) {
        this.code = code;
        return this;
    }

    public T getResult() {
        return result;
    }

    public PackageResult setResult(T result) {
        this.result = result;
        return this;
    }
}
