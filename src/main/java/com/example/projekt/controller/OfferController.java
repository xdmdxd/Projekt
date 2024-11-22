package com.example.projekt.controller;

import com.example.projekt.model.Offer;
import com.example.projekt.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // List all offers
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offer_list";
    }

    // View offer details by ID
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Offer offer = offerService.getOfferById(id);
        if (offer != null) {
            model.addAttribute("offer", offer);
            return "offer_detail";
        }
        return "redirect:/offers/";
    }

    // Delete offer by ID
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        offerService.deleteOffer(id);
        return "redirect:/offers/";
    }

    // Show offer creation form
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("offer", new Offer());
        model.addAttribute("edit", false);
        return "offer_edit";
    }

    // Edit offer by ID
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Offer offer = offerService.getOfferById(id);
        if (offer != null) {
            model.addAttribute("offer", offer);
            model.addAttribute("edit", true);
            return "offer_edit";
        }
        return "redirect:/offers/";
    }

    // Save offer (create or update)
    @PostMapping("/save")
    public String save(@Valid Offer offer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "offer_edit";
        }
        offerService.saveOffer(offer);
        return "redirect:/offers/";
    }
}
