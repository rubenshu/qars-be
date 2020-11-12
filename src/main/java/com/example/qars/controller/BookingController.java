package com.example.qars.controller;


import com.example.qars.entity.Booking;
import java.text.SimpleDateFormat;  
import java.util.Date;

import com.example.qars.entity.Car;
import com.example.qars.entity.Customer;
import com.example.qars.service.BookingService;
import com.example.qars.service.CarService;
import com.example.qars.service.CustomerService;
import com.example.qars.service.UserService;
import org.apache.coyote.Response;
import org.json.JSONObject;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/booking")
public class    BookingController {
    private final BookingService bookingService;
    private final CarService carService;
    private final CustomerService customerService;
    private final UserService userService;

    public BookingController(BookingService bookingService,  CarService carService, CustomerService customerService, UserService userService) {
        this.bookingService = bookingService;
        this.carService = carService;
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping("")
    public List<Booking> findAll() {
        return this.bookingService.findAll();
    }

    @PostMapping("/mybookings")
    public List<Booking> allBookingsFromCustomer(@RequestBody String data) throws Error {
        JSONObject jsondata = new JSONObject(data);
        int id = jsondata.getInt("id");

        return this.bookingService.allBookingsFromCustomer(id);
    }

    @PostMapping("")
    @Transactional
    public ResponseEntity<Object> addBooking(@RequestBody String data) throws Error, ChangeSetPersister.NotFoundException {
        // Attempt to save product
        JSONObject jsondata = new JSONObject(data);
        int id = jsondata.getInt("carId");
        Booking newBooking = new Booking();
        int userid = jsondata.getInt("userId");

        Car car = carService.findById(id);

        newBooking.setCustomer(customerService.loadCustomerById(userid));
        newBooking.setBookingDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        //TODO: calculate total price, put price as int instead of string
        newBooking.setTotalAmount(jsondata.getInt("pricePh"));
        newBooking.setCar(car);
        
        newBooking.setBeginDate(jsondata.getString("beginDate"));
        newBooking.setEndDate(jsondata.getString("endDate"));
        newBooking.setPickUpLocationCode(car.getEstablishment().getLocation().getCity());
        newBooking.setDropOffLocationCode(jsondata.getString("dropoffLocation"));

        if (!newBooking.getCustomer().getDriverslicense_status().equals("GEACCEPTEERD")) {
            return ResponseEntity.status(401).body(newBooking);
        }

        if (bookingService.save(newBooking) && carService.changeAvailabilty(id) ) return ResponseEntity.ok(newBooking);
            // Else throw error
        else throw new Error("Failed to create booking ");
    }





}
