package com.example.projekt.controller;

import com.example.projekt.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    List<Car> cars=new ArrayList<>();


    @GetMapping
    public String list(Model model) {
        cars.add(new Car("xd","modra","40l","5"));
        model.addAttribute("cars",cars);
        return "list";
    }
}
