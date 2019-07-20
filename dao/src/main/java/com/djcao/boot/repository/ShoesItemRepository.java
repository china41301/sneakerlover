package com.djcao.boot.repository;

import java.util.List;

import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoesItemRepository extends JpaRepository<ShoesItem,Long> {

    @Query(value = "select * from shoes_item",nativeQuery = true)
    Page<ShoesItem> getShoesItems(Pageable pageable);

    @Query(value = "select t from ShoesItem t where t.itemId = :itemId")
    ShoesItem findOneByItemId(@Param("itemId") String itemId);

    @Modifying
    @Query(value = "update ShoesItem set status = :status where itemId in :itemIdList")
    int updateStatusByItemIdList(@Param("itemIdList")List<String> itemIdList, @Param("status")Integer status);
}
