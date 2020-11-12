package com.example.qars.dao;


import com.example.qars.entity.Customer;
import com.example.qars.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Blob;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "FROM Customer WHERE user_id=:id")
    Customer getCustomerById(@Param("id") int id);

    @Modifying
    @Query("UPDATE Customer SET licensephoto =:blob, driverslicense_status = 'INGEDIEND' WHERE user_id=:id")
    int uploadLicense(@Param("blob") Blob blob, @Param("id") int id);

    @Query(value = "SELECT COUNT(*) FROM Customer", nativeQuery = true)
    int getCount();
}
