package com.example.projekt.service;

import com.example.projekt.model.Car;
import com.example.projekt.repository.CarRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {


    CarRepository carRepository;
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }



    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void deleteCar(long id) {
        Optional<Car> car=carRepository.findById(id);
        if (car.isPresent()) {carRepository.delete(car.get());}

    }

}