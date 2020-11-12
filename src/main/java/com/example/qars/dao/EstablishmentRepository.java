package com.example.qars.dao;

import com.example.qars.entity.Establishment;
import com.example.qars.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {

    @Query(value = "FROM Establishment WHERE location_id = :location_id")
    List<Establishment> getEstablishmentsByLocation(@Param("location_id") int location_id);
}
