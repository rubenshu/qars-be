package com.example.qars.dao;

import com.example.qars.entity.Booking;
import com.example.qars.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import static org.springframework.http.HttpHeaders.FROM;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query(value = "FROM Location WHERE city = :city")
    Location getLocationByCity(@Param("city") String city);
}
