package com.example.qars.controller;

import com.example.qars.entity.Address;
import com.example.qars.entity.Customer;
import com.example.qars.service.AddressService;
import com.example.qars.service.CustomerService;
import org.json.JSONObject;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;
import java.sql.Blob;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final AddressService addressService;

    public CustomerController(CustomerService customerService, AddressService addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return this.customerService.findAll();
    }

    @GetMapping("/count")
    public int getCustomersAmount() {
        return this.customerService.getCount();
    }

    @PostMapping("/profile")
    public ResponseEntity<Object> findProfile(@RequestBody String data) throws ChangeSetPersister.NotFoundException {
        // Maak er een json van en doe dan alle customergegevens + adresgegevens hierin. Ook alle oude boekingen?

        JSONObject jsondata = new JSONObject(data);
        int id = jsondata.getInt("id");

        // De response
        HashMap<String, String> response = new HashMap<>();

        // id is hier de user_id die wordt meegstuurd uit vanuit de front-end jwt token
        final Customer customer = customerService.loadCustomerById(id);
        final Address address = addressService.loadAddressById(id); // by customer id

        // Customer info
        response.put("given_name", customer.getGivenName());
        response.put("family_name", customer.getFamilyName());
        response.put("email", customer.getEmailaddress());
        response.put("phone", Integer.toString(customer.getPhoneNumber()));
        response.put("driverlicense_status", customer.getDriverslicense_status());
        response.put("customer_id", Integer.toString(customer.getId()));

        // Adres info
        response.put("street", address.getStreet());
        response.put("postal", address.getPostalCode());
        response.put("number", Integer.toString(address.getNumber()));
        response.put("city", address.getCity());
        
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PutMapping("/upload")
    public ResponseEntity<Object> uploadLicense(@RequestBody String data) throws Exception {
        JSONObject jsondata = new JSONObject(data);

        int id = jsondata.getInt("id");
        byte[] imgurl = jsondata.getString("blob").getBytes();
        Blob blob = new SerialBlob(imgurl);

        // De response
        HashMap<String, String> response = new HashMap<>();

        final boolean changed = customerService.uploadLicense(id, blob);

        if (changed) {
            response.put("response", "license succesfully uploaded");
            return ResponseEntity.ok(response);
        }
        else throw new Exception("License uploaden mislukt");
    }


}
