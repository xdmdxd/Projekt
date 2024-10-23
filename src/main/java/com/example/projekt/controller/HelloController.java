package com.example.projekt.controller;

import com.example.projekt.service.CarService;
import com.example.projekt.service.DriverService;

public class HelloController {

    private CarService carService;

    private DriverService driverService;

    public HelloController(CarService carService, DriverService driverService) {
        this.carService = carService;
        this.driverService = driverService;
    }
}
