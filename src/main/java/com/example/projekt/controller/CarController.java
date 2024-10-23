package com.example.projekt.controller;

import com.example.projekt.model.Car;
import com.example.projekt.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;


    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("cars",carService.getAllCars());
        return "car_list";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index) {
        Car car=carService.getCarById(index);
        if (car!=null) {
            model.addAttribute("car",car);
            return "car_detail";
        }

        return "redirect:/cars/";
    }


    @GetMapping("/delete/{index}")
    public String delete(Model model, @PathVariable int index) {
        carService.deleteCarById(index);
        return "redirect:/cars/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car",new Car());
        model.addAttribute("edit",false);
        return "car_edit";
    }

    @GetMapping("/edit/{index}")
    public String edit(Model model,@PathVariable int index) {
        Car car=carService.getCarById(index);
        if (car!=null) {
            model.addAttribute("car",car);
            model.addAttribute("edit",true);
            return "car_edit";
        }
        return "redirect:/cars/";
    }

    @PostMapping("/save")
    public String save(@Valid Car car, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("edit",true);
            return "car_edit";
        }
        carService.saveCar(car);
        return "redirect:/cars/";
    }

}
