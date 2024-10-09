package com.example.projekt.controller;

import com.example.projekt.model.Car;
import com.example.projekt.service.CarService;
import com.example.projekt.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

    private CarService carService;


    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("cars",carService.getAllCars());
        return "list";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index) {
        Car car=carService.getCarById(index);
        if (car!=null) {
            model.addAttribute("car",car);
            return "detail";
        }

        return "redirect:/";
    }


    @GetMapping("/delete/{index}")
    public String delete(Model model, @PathVariable int index) {
        carService.deleteCarById(index);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car",new Car());
        model.addAttribute("edit",false);
        return "edit";
    }

    @GetMapping("/edit/{index}")
    public String edit(Model model,@PathVariable int index) {
        Car car=carService.getCarById(index);
        if (car!=null) {
            model.addAttribute("car",car);
            model.addAttribute("edit",true);
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Car car) {

        carService.saveCar(car);
        return "redirect:/";
    }

}
