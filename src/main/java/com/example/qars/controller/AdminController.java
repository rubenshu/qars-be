package com.example.qars.controller;

import com.example.qars.entity.Customer;
import com.example.qars.service.AdminService;
import org.apache.coyote.Response;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/license-requests")
    public List<Customer> getLicenseRequests() {
        return this.adminService.getLicenseRequests();
    }

    @PostMapping("/license-response")
    public ResponseEntity<Object> licenseRequestResponse(@RequestBody String data) throws Exception {
        JSONObject jsondata = new JSONObject(data);
        int customerId = jsondata.getInt("customerid");
        String response = jsondata.getString("response");

        HashMap<String, String> responsemsg = new HashMap<>();
        final boolean changed = this.adminService.licenseRequestResponse(customerId, response);

        if (changed) {
            responsemsg.put("response", "License status succesfully updated");
            return ResponseEntity.ok(responsemsg);
        }
        else throw new Exception("License status updaten failed");

    }
}
