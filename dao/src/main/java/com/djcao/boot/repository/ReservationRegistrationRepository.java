package com.djcao.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-03
 */
public interface ReservationRegistrationRepository extends JpaRepository<ReservationRegistration,
    Long>,JpaSpecificationExecutor {

    @Query(value = "select t from ReservationRegistration t where t.itemId in :itemIdList")
    List<ReservationRegistration> findByItemId(@Param("itemIdList") List<Long> itemIdList);

}
