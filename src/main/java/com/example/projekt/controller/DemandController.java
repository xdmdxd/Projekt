package com.example.projekt.controller;

import com.example.projekt.model.Demand;  // Use Demand instead of Offer
import com.example.projekt.service.DemandService;  // Use DemandService instead of OfferService
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/demands")  // Use "/demands" instead of "/offers"
public class DemandController {  // Use DemandController instead of OfferController

    private DemandService demandService;  // Use DemandService instead of OfferService

    @Autowired
    public DemandController(DemandService demandService) {  // Constructor injection for DemandService
        this.demandService = demandService;
    }

    // List all demands
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("demands", demandService.getAllDemand());  // Fetch demands
        return "demand_list";  // Return "demand_list" view
    }

    // View demand details by ID
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Demand demand = demandService.getDemandById(id);  // Fetch demand by ID
        if (demand != null) {
            model.addAttribute("demand", demand);
            return "demand_detail";  // Return "demand_detail" view
        }
        return "redirect:/demands/";  // Redirect to demand list if not found
    }

    // Delete demand by ID
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        demandService.deleteDemand(id);  // Delete demand by ID
        return "redirect:/demands/";  // Redirect to demand list
    }

    // Show demand creation form
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("demand", new Demand());  // Initialize a new Demand object
        model.addAttribute("edit", false);  // Indicate it's not an edit form
        return "demand_edit";  // Return "demand_edit" view for creation
    }

    // Edit demand by ID
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Demand demand = demandService.getDemandById(id);  // Fetch demand by ID for editing
        if (demand != null) {
            model.addAttribute("demand", demand);
            model.addAttribute("edit", true);  // Indicate it's an edit form
            return "demand_edit";  // Return "demand_edit" view for editing
        }
        return "redirect:/demands/";  // Redirect to demand list if not found
    }

    // Save demand (create or update)
    @PostMapping("/save")
    public String save(@Valid Demand demand, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {  // If there are validation errors
            model.addAttribute("edit", true);  // Indicate it's an edit form
            return "demand_edit";  // Return "demand_edit" view with errors
        }
        demandService.saveDemand(demand);  // Save or update the demand
        return "redirect:/demands/";  // Redirect to the demand list after save
    }
}
