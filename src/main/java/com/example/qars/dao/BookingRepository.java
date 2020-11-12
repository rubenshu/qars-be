package com.example.qars.dao;


import com.example.qars.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "FROM Booking WHERE customer_id =:id")
    List<Booking> allBookingsFromCustomer(int id);
}
