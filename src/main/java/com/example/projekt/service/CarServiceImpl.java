package com.example.projekt.service;

import com.example.projekt.model.Car;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CarServiceImpl implements CarService {

    ArrayList<Car> cars = new ArrayList<>();

    @Override
    public ArrayList<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getCarById(int id) {
        Car car = null;
        if(id > -1 && id < getCount()){
            car = cars.get(id);
        }
        return car;
    }

    @Override
    public void saveCar(Car car) {
        if(car.getId() > -1)
            cars.remove(car.getId());
        //car.setId(-1);
        cars.add(car);
    }

    @Override
    public void deleteCar(int id) {
        if(id > -1 && id < getCount()){
            cars.remove(id);
        }
    }

    @Override
    public int getCount() {
        return cars.size();
    }
}