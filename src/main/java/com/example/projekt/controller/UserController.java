package com.example.projekt.controller;

import com.example.projekt.model.User;
import com.example.projekt.repository.UserRepository;
import com.example.projekt.service.DemandService;
import com.example.projekt.service.OfferService;
import com.example.projekt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final OfferService offerService;
    private final DemandService demandService;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, OfferService offerService, DemandService demandService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.offerService = offerService;
        this.demandService = demandService;
    }

    // List all users
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "user_list"; // Stránka s výpisem všech uživatelů
    }

    // View details of the logged-in user
    @GetMapping("/detail")
    public String loggedUserDetail(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Redirect to login if no user is authenticated
        }

        // Extract username from the authenticated principal
        String username = authentication.getName();

        // Find user by username
        User user = userRepository.findByUsername(username); // Ensure this method exists in your repository
        if (user != null) {
            model.addAttribute("user", user);
            return "user_detail"; // Ensure this template exists
        }

        return "redirect:/";
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



    @PostMapping("/save")
    public String save(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", user.getId() != null);
            return "user_edit";
        }

        // Check if the email is already in use by another user
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null && !existingUser.getId().equals(user.getId())) {
            model.addAttribute("emailError", "Email is already in use.");
            model.addAttribute("edit", user.getId() != null);
            return "user_edit";
        }

        // Handle password updates (e.g., allow blank passwords to mean "no change")
        if (user.getPassword() != null && user.getPassword().isEmpty()) {
            user.setPassword(null);
        }

        userService.saveUser(user);
        return "redirect:/users/";
    }



    @GetMapping("/my-offers")
    public String getMyOffers(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Pokud není uživatel přihlášen
        }

        String username = authentication.getName(); // Získání uživatelského jména
        User user = userService.getUserByUsername(username); // Metoda v UserService pro získání uživatele podle username

        if (user == null) {
            return "redirect:/login"; // Pokud uživatel není nalezen, přesměrujte na přihlášení
        }

        model.addAttribute("offers", user.getOffers()); // Přidejte uživatelovy nabídky do modelu
        model.addAttribute("demands", user.getDemands()); // Přidejte uživatelovy poptávky do modelu

        return "user_offersdemands"; // Název šablony HTML pro zobrazení nabídek a poptávek
    }


    @PostMapping("/deleteAccount")
    public String deleteAccount(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // Get the logged-in username
            User user = userRepository.findByUsername(username);
            if (user != null) {
                userService.deleteUser(user.getId()); // Delete the user by ID
            }
        }
        return "redirect:/logout"; // Log out the user after account deletion
    }


}
