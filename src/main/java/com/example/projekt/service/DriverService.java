package com.example.projekt.service;

import com.example.projekt.model.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface DriverService {
    List<Driver> getAllDrivers();
    Driver getDriverById(long id);
    void saveDriver(Driver Driver);
    void deleteDriver(long id);
}
