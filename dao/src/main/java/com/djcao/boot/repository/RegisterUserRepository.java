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
}
