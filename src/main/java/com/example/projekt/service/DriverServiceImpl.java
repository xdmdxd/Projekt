package com.example.projekt.service;

import com.example.projekt.model.Car;
import com.example.projekt.model.Driver;
import com.example.projekt.repository.CarRepository;
import com.example.projekt.repository.DriverRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    DriverRepository driverRepository;
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriverById(long id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDriver(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(long id) {
        Optional<Driver> driver=driverRepository.findById(id);
        if (driver.isPresent()) {driverRepository.delete(driver.get());}
    }

}