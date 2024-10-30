package com.example.projekt.service;

import com.example.projekt.model.Driver;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class DriverServiceImpl implements DriverService {

    ArrayList<Driver> drivers = new ArrayList<>();

    @Override
    public ArrayList<Driver> getAllDrivers() {
        return drivers;
    }

    @Override
    public Driver getDriverById(int id) {
        Driver driver = null;
        if(id > -1 && id < getCount()){
            driver = drivers.get(id);
        }
        return driver;
    }

    @Override
    public void saveDriver(Driver driver) {
        if(driver.getId() > -1)
            drivers.remove(driver.getId());
        //driver.setId(-1);
        drivers.add(driver);
    }

    @Override
    public void deleteDriver(int id) {
        if(id > -1 && id < getCount()){
            drivers.remove(id);
        }
    }

    @Override
    public int getCount() {
        return drivers.size();
    }
}