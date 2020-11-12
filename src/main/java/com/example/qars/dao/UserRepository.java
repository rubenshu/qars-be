package com.example.qars.dao;

import com.example.qars.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer > {

    @Query(value = "FROM User WHERE username=:username")
    User getAccountByUsername(@Param("username") String username);

}
