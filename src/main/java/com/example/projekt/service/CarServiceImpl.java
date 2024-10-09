package com.example.projekt.service;

import com.example.projekt.model.Car;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CarServiceImpl implements CarService {
    ArrayList<Car> cars=new ArrayList<>();

    @Override
    public ArrayList<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getCarById(int id) {
        if(id>-1&& id<cars.size()){
            Car car=cars.get(id);
            return car;
        }
        return null;
    }

    @Override
    public void deleteCarById(int id) {
        if (id > -1 && id<cars.size()) {
            cars.remove(id);

        }
    }

    @Override
    public void saveCar(Car car){
        if (car.getId()>-1) {
            cars.remove(car.getId());
        }
        cars.add(car);
    }


}
