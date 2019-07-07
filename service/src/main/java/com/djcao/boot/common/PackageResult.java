package com.djcao.boot.common;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

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

    private int total;

    private static PackageResult create(boolean success, int code){
        return create(success, StringUtils.EMPTY,code);
    }

    private static PackageResult create(boolean success, String message, int code){
        return new PackageResult().setSuccess(success).setMessage(message).setCode(code);
    }

    private static <T> PackageResult<T> create(boolean success, String message, int code, T data){
        return new PackageResult<T>().setSuccess(success).setMessage(message).setCode(code).setResult(data);
    }

    public static <T> PackageResult<T> success(T data){
        return create(Boolean.TRUE, StringUtils.EMPTY, CodeDef.SUCCESS,data);
    }

    public static PackageResult success(){
        return create(Boolean.TRUE, CodeDef.SUCCESS);
    }

    public static PackageResult error(String message){
        return create(Boolean.FALSE,message, CodeDef.ERROR);
    }

    public boolean isSuccess() {
        return success;
    }

    public PackageResult<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public PackageResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }


    public int getPageNo() {
        return pageNo;
    }

    public PackageResult<T> setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PackageResult<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getCode() {
        return code;
    }

    public PackageResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public T getResult() {
        return result;
    }

    public PackageResult<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public PackageResult<T> setTotal(int total) {
        this.total = total;
        return this;
    }

    public PackageResult<T> setPage(Page page){
        this.code = CodeDef.SUCCESS;
        this.success = Boolean.TRUE;
        this.result = (T)page.getContent();
        this.pageNo = page.getPageable().getPageNumber();
        this.pageSize = page.getPageable().getPageSize();
        this.total = page.getTotalPages() * this.pageSize;
        return this;
    }
}
