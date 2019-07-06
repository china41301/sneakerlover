package com.djcao.boot.common;

import lombok.Data;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-03
 */
public class BaseSo {

    private int pageNum;

    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
