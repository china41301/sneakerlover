package com.djcao.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-03
 */
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select * from user where account = ?1 limit 1",nativeQuery = true)
    User findByAccount(String account);
}
