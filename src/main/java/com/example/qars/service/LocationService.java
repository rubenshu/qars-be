package com.example.qars.service;

import com.example.qars.dao.BookingRepository;
import com.example.qars.dao.LocationRepository;
import com.example.qars.entity.Location;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService extends BaseService<Location> {


    private LocationRepository repository;

    public LocationService(LocationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional
    public Location getLocationByCity(String city) {
        return this.repository.getLocationByCity(city);
    }
}
