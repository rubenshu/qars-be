package com.example.qars.controller;

import com.example.qars.entity.Address;
import com.example.qars.entity.Customer;
import com.example.qars.entity.User;
import com.example.qars.service.AddressService;
import com.example.qars.service.CustomerService;
import com.example.qars.service.UserService;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/register")
@RestController
public class RegisterController {
    private final CustomerService customerService;
    private final AddressService addressService;
    private final UserService userService;

    public RegisterController(CustomerService customerService, AddressService addressService, UserService userService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.userService = userService;
    }

    @PostMapping("")
    @Transactional
    public ResponseEntity<Object> Register(@RequestBody String data) throws Exception {
        JSONObject jsondata = new JSONObject(data);

        // password hash
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User(jsondata.getString("username"), encoder.encode(jsondata.getString("password")), "customer");
        Address address = new Address(jsondata.getString("city"), jsondata.getString("street"), jsondata.getInt("housenumber"), jsondata.getString("postal")); address.setUser(user);
        Customer customer = new Customer(jsondata.getString("first_name"), jsondata.getString("last_name"), jsondata.getInt("telefoon"), jsondata.getString("email")); customer.setUser(user);

        if (userService.save(user) && addressService.save(address) && customerService.save(customer)) return ResponseEntity.ok(user);
        else throw new Exception("Registratie mislukt");
    }
}
