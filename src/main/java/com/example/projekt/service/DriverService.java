package com.example.projekt.service;

import com.example.projekt.model.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface DriverService {


    ArrayList<Driver> getAllDrivers();


    Driver getDriverById(int id);

    void deleteDriverById(int id);
    void saveDriver(Driver driver);


}
