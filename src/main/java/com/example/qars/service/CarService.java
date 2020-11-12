package com.example.qars.service;

import com.example.qars.dao.CarRepository;
import com.example.qars.entity.Car;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService extends BaseService<Car> {
    private CarRepository repository;

    public CarService(CarRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional
    public boolean changeAvailabilty(int id) throws Error{
        int car = repository.changeAvailabilty(id);
        if(car != 0) return true;
        else throw new Error("kon niet veranderen");
    }

    @Transactional
    public List<Car> getCarsByEstablishment(int establishment_id) {
        return this.repository.getCarsByEstablishment(establishment_id);
    }

    @Transactional
    public List<Car> getCarsByPickUpDate() throws Exception {
        return this.repository.getCarsByPickUpDate();
    }

    @Transactional
    public List<Car> getMostUsedCars() throws Exception {
        List<Car> cars = repository.getMostUsedCars();
        if (cars != null) {
            return cars;
        }
        else {
            throw new Error("No cars found");
        }
    }
}
