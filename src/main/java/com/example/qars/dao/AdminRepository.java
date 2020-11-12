package com.example.qars.dao;

import com.example.qars.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "FROM Customer WHERE driverslicense_status = 'INGEDIEND'")
    List<Customer> getLicenseRequests();

    @Modifying
    @Query("UPDATE Customer SET driverslicense_status ='GEACCEPTEERD' WHERE id=:id")
    int acceptLicenseRequest(@Param("id") int id);

    @Modifying
    @Query("UPDATE Customer SET driverslicense_status ='GEWEIGERD' WHERE id=:id")
    int declineLicenseRequest(@Param("id") int id);
}
