package com.djcao.boot.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoesItemRepository extends JpaRepository<ShoesItem,Long> {

    @Query(value = "select * from shoes_item",nativeQuery = true)
    List<ShoesItem> getShoesItems(Pageable pageable);
}
