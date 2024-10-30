package com.example.projekt.controller;

import com.example.projekt.model.Car;
import com.example.projekt.model.Driver;
import com.example.projekt.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }


    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "driver_list";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index){
        Driver driver = driverService.getDriverById(index);
        if(driver != null){
            model.addAttribute("driver", driver);
            return "driver_detail";
        }
        return "redirect:/drivers/";
    }

    @GetMapping("/delete/{index}")
    public String delete(Model model, @PathVariable int index){
        driverService.deleteDriver(index);
        return "redirect:/drivers/";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("driver", new Driver());
        model.addAttribute("edit", false);
        return "driver_edit";
    }

    @GetMapping("/edit/{index}")
    public String edit(Model model, @PathVariable int index){
        Driver driver = driverService.getDriverById(index);
        if(driver != null){
            driver.setId(index);
            model.addAttribute("driver", driver);
            model.addAttribute("edit", true);
            return "driver_edit";
        }
        return "redirect:/drivers/";
    }

    @PostMapping("/save")
    public String save(@Valid Driver driver, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", true);
            return "driver_edit";
        }
        driverService.saveDriver(driver);
        return "redirect:/drivers/";
    }

}
