package com.example.projekt.service;

import com.example.projekt.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CarService {
    ArrayList<Car> getAllCars();
    Car getCarById(int id);
    void saveCar(Car car);
    void deleteCar(int id);
    int getCount();
}
