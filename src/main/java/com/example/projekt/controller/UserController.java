package com.example.projekt.controller;

import com.example.projekt.model.User;
import com.example.projekt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // List all users
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "user_edit"; // Stránka s výpisem všech uživatelů
    }

    // View user details by ID
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "user_detail"; // Stránka s detailem uživatele
        }
        return "redirect:/users/";
    }

    // Delete user by ID
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/users/";
    }

    // Show user creation form
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("edit", false);
        return "user_edit"; // Stránka s formulářem pro vytvoření uživatele
    }

    // Edit user by ID
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            return "user_edit"; // Stránka s formulářem pro editaci uživatele
        }
        return "redirect:/users/";
    }

    // Save user (create or update)
    @PostMapping("/save")
    public String save(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "user_edit";
        }
        userService.saveUser(user);
        return "redirect:/users/";
    }
}
