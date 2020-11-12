package com.example.qars.service;


import com.example.qars.dao.BookingRepository;
import com.example.qars.entity.Booking;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService extends BaseService<Booking> {

    private BookingRepository repository;

    public BookingService(BookingRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional
    public List<Booking> allBookingsFromCustomer(int id) {
        return this.repository.allBookingsFromCustomer(id);
    }

}
