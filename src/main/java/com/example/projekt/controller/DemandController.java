package com.example.projekt.controller;

import com.example.projekt.model.Demand;  // Use Demand instead of Offer
import com.example.projekt.model.User;
import com.example.projekt.repository.UserRepository;
import com.example.projekt.service.DemandService;  // Use DemandService instead of OfferService
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/demands")  // Use "/demands" instead of "/offers"
public class DemandController {  // Use DemandController instead of OfferController

    private DemandService demandService;  // Use DemandService instead of OfferService
    private UserRepository userRepository;

    @Autowired
    public DemandController(DemandService demandService, UserRepository userRepository) {  // Constructor injection for DemandService
        this.demandService = demandService;
        this.userRepository = userRepository;
    }

    // List all demands
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("demands", demandService.getAllDemand());  // Fetch demands
        return "demand_list";  // Return "demand_list" view
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id, Authentication authentication) {
        Demand demand = demandService.getDemandById(id);
        if (demand != null) {
            String loggedInUsername = authentication.getName();
            boolean isOwner = demand.getUser().getUsername().equals(loggedInUsername); // Check ownership
            model.addAttribute("demand", demand);
            model.addAttribute("isOwner", isOwner); // Pass ownership flag to the template
            return "demand_detail";
        }
        return "redirect:/demands/";
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

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }

        // Fetch the demand by ID
        Demand demand = demandService.getDemandById(id);

        if (demand != null) {
            // Get the logged-in user's username
            String loggedInUsername = authentication.getName();

            // Check if the logged-in user is the owner of the demand
            if (!demand.getUser().getUsername().equals(loggedInUsername)) {
                return "redirect:/demands/"; // Redirect to the demand list if not authorized
            }

            // If the user is authorized, display the edit form
            model.addAttribute("demand", demand);
            model.addAttribute("edit", true); // Indicate it's an edit form
            return "demand_edit"; // Return "demand_edit" view for editing
        }

        return "redirect:/demands/"; // Redirect to the demand list if the demand is not found
    }


    @PostMapping("/save")
    public String saveDemand(@ModelAttribute Demand demand, Authentication authentication) {
        String username = authentication.getName(); // Get logged-in user's username
        User user = userRepository.findByUsername(username);
        if (user != null) {
            demand.setUser(user); // Associate user with the demand
            demandService.saveDemand(demand);
        }
        return "redirect:/demands/";
    }

}
