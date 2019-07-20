package com.djcao.boot.service;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.ShoesItem;
import com.djcao.boot.repository.User;
import com.djcao.boot.vo.ShoesItemVO;

import java.util.List;

public interface ShoesItemService {
    /**
     * 根据id获取鞋子详情
     * @param id
     * @return
     */
    PackageResult<ShoesItemVO> getShoesItemById(Long id, User user) throws Exception;

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
    PackageResult<List<ShoesItem>> addNewShoesItem(List<ShoesItem> ShoesItems) throws Exception;
}
