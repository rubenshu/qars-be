package com.example.qars.dao;

import com.example.qars.entity.Address;
import com.example.qars.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value = "FROM Address WHERE user_id=:id")
    Address getAddressById(@Param("id") int id);

}
