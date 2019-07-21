package com.djcao.boot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-03
 */
public interface RegisterUserRepository extends JpaRepository<RegisterUser,Long> {

    @Query(value = "select t from RegisterUser t where t.userId = :userId")
    Page<RegisterUser> findRegisterUserByUserId(@Param("userId")Long userId, Pageable pageable);

    @Query(value = "select t from RegisterUser t where t.id in :ids")
    List<RegisterUser> findRegisterUsersByIds(@Param("ids")List<Long> ids);

    @Query(value = "select * from register_user ru WHERE ru.user_id = ?1 "
        + "and  not EXISTS (select 1 from reservation_registration rr where rr.register_user_id = ru.id and item_id = ?2) "
        + "limit ?3",nativeQuery = true)
    List<RegisterUser> findNotReservationByUserId(@Param("userId")Long userId,@Param("itemId") String itemId,@Param("size") int size);

    @Query(value = "select count(ru.id) from register_user ru WHERE ru.user_id = ?1 "
        + "and  not EXISTS (select 1 from reservation_registration rr where rr.register_user_id = ru.id and item_id = ?2) ",nativeQuery = true)
    int findNotReservationByUserIdCount(@Param("userId")Long userId, @Param("itemId") Long itemId);

    @Query(value = "select t from RegisterUser t where t.userName = :userName")
    RegisterUser findByUserName(@Param("userName") String userName);
}
