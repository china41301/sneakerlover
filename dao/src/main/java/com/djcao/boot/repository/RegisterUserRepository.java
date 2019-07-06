package com.djcao.boot.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-03
 */
public interface RegisterUserRepository extends JpaRepository<RegisterUser,Long> {

    @Query(value = "select * from register_user where user_id = ?1",nativeQuery = true)
    List<RegisterUser> findRegisterUserByUserId(Long userId,Pageable pageable);
}
