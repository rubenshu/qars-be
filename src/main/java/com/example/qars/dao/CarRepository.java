package com.example.qars.dao;

import com.example.qars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Modifying
    @Query("UPDATE Car SET available = false, counter = counter+1 WHERE id=:id")
        int changeAvailabilty(@Param("id") int id);

    @Query(value = "SELECT * FROM Car WHERE available = true ORDER BY counter DESC LIMIT 6", nativeQuery = true)
        List<Car> getMostUsedCars();

    @Query(value = "FROM Car WHERE available = true AND establishment_id = :establishment_id")
    List<Car> getCarsByEstablishment(@Param("establishment_id") int establishment_id);

    @Query(value = "SELECT * FROM Car WHERE available = true", nativeQuery = true)
    List<Car> getCarsByPickUpDate();
}
