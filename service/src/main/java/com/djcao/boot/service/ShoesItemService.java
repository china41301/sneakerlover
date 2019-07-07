package com.djcao.boot.service;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.ShoesItem;

import java.util.List;

public interface ShoesItemService {
    /**
     * 根据id获取鞋子详情
     * @param id
     * @return
     */
    PackageResult<ShoesItem> getShoesItemById(Long id);

    /**
     * 获取所有鞋子的列表
     * @return
     */
    PackageResult<List<ShoesItem>> getShoesItem(int pageNo, int pageSize);

    /**
     * 加入上新的鞋子
     * @param ShoesItems
     * @return
     */
    PackageResult<String> addNewShoesItem(List<ShoesItem> ShoesItems) throws Exception;
}
