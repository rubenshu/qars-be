package com.example.qars.controller;

import com.example.qars.controller.exception.IdNotFoundException;
import com.example.qars.entity.Car;
import com.example.qars.service.CarService;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/car")
public class CarController {
	private final CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("")
	public List<Car> findAll() {
		return this.carService.findAll();
	}

	@GetMapping(path = "/top6")
	public List<Car> getMostUsedCars() throws Exception {
		return this.carService.getMostUsedCars();
	}

	@PostMapping("/byestablishment")
	public List<Car> getCarsByEstablishment(@RequestBody String data) {
		JSONObject jsondata = new JSONObject(data);
		int establishment_id = jsondata.getInt("establishment_id");
		return this.carService.getCarsByEstablishment(establishment_id);
	}

	@GetMapping("bypickupdate")
	public List<Car> getCarsByPickUpDate() throws Exception {
		return this.carService.getCarsByPickUpDate();

	}

	@PostMapping("/byid")
	public Car getCarById(@RequestBody String data) {
		JSONObject jsondata = new JSONObject(data);
		Car car = this.carService.findById(jsondata.getInt("car_id"));
		return this.carService.findById(jsondata.getInt("car_id"));
	}



}
