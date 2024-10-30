package com.example.projekt.service;

import com.example.projekt.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface CarService {
    List<Car> getAllCars();
    Car getCarById(long id);
    void saveCar(Car car);
    void deleteCar(long id);
}
