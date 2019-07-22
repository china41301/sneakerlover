package com.djcao.boot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select t from ReservationRegistration t where t.userId = :userId group by t.itemId")
    Page<ReservationRegistration> findByUserId(@Param("userId")Long userId, Pageable pageable);

    @Query(value = "select t from ReservationRegistration t where t.userId = :userId and t.itemId = :itemId")
    Page<ReservationRegistration> findByUserIdAndItemId(@Param("userId")Long userId,@Param("itemId")String itemId,Pageable pageable);

    @Query(value = "select count(t.id) from ReservationRegistration t where t.userId = :userId and t.itemId = :itemId and t.status = :status")
    Integer countSignSuccessNumberByUserIdAndItemId(@Param("userId")Long userId,@Param("itemId")String itemId,@Param("status")Integer status);

    @Query(value = "select t from ReservationRegistration t where t.itemId = :itemId and t.status = :status")
    List<ReservationRegistration> findByItemIdAndStatus(@Param("itemId")String itemId,@Param("status")Integer status);

}
