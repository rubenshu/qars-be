package com.example.qars.controller;

import com.example.qars.entity.Establishment;
import com.example.qars.entity.Location;
import com.example.qars.service.EstablishmentService;
import com.example.qars.service.LocationService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/establishment")
public class EstablishmentController {

    private final EstablishmentService establishmentService;
    private final LocationService locationService;

    public EstablishmentController(EstablishmentService establishmentService, LocationService locationService) {
        this.establishmentService = establishmentService;
        this.locationService = locationService;
    }

    @GetMapping("")
    public List<Establishment> findAll() { return this.establishmentService.findAll(); }

    @PostMapping("/bylocation")
    public List<Establishment> getEstablishmentsByLocation(@RequestBody String data) {
        JSONObject jsondata = new JSONObject(data);
        String city = jsondata.getString("location");
        Location location = locationService.getLocationByCity(city);

        return this.establishmentService.getEstablishmentsByLocation(location);
    }
}
