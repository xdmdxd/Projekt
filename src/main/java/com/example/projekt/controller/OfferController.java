package com.example.projekt.controller;

import com.example.projekt.model.Offer;
import com.example.projekt.model.User;
import com.example.projekt.repository.UserRepository;
import com.example.projekt.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private OfferService offerService;
    private UserRepository userRepository;

    @Autowired
    public OfferController(OfferService offerService, UserRepository userRepository) {
        this.offerService = offerService;
        this.userRepository = userRepository;
    }

    // List all offers
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offer_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id, Authentication authentication) {
        Offer offer = offerService.getOfferById(id);
        if (offer != null) {
            String loggedInUsername = authentication.getName();
            boolean isOwner = offer.getUser().getUsername().equals(loggedInUsername); // Check ownership
            model.addAttribute("offer", offer);
            model.addAttribute("isOwner", isOwner); // Pass ownership flag to the template
            return "offer_detail";
        }
        return "redirect:/offers/";
    }

    // Delete offer by ID
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, Authentication authentication) {
        Offer offer = offerService.getOfferById(id);

        if (offer != null) {
            String loggedInUsername = authentication.getName(); // Get logged-in user's username
            if (!offer.getUser().getUsername().equals(loggedInUsername)) {
                // Redirect if the user is not the owner of the offer
                return "redirect:/offers/?error=not_authorized";
            }

            offerService.deleteOffer(id); // Proceed with deletion if authorized
        }
        return "redirect:/offers/";
    }

    // Show offer creation form
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("offer", new Offer());
        model.addAttribute("edit", false);
        return "offer_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id, Authentication authentication) {
        // Fetch the offer by its ID
        Offer offer = offerService.getOfferById(id);
        if (offer != null) {
            String loggedInUsername = authentication.getName(); // Get the logged-in user's username
            if (!offer.getUser().getUsername().equals(loggedInUsername)) {
                // Redirect if the logged-in user didn't create the offer
                return "redirect:/offers/";
            }

            // Add attributes for editing the offer
            model.addAttribute("offer", offer);
            model.addAttribute("edit", true);
            return "offer_edit";
        }
        return "redirect:/offers/";
    }

    @PostMapping("/save")
    public String saveOffer(@ModelAttribute Offer offer, Authentication authentication) {
        String username = authentication.getName(); // Získání přihlášeného uživatele
        User user = userRepository.findByUsername(username);
        if (user != null) {
            offer.setUser(user); // Přiřazení uživatele k nabídce
            offerService.saveOffer(offer);
        }
        return "redirect:/offers/";
    }
}
