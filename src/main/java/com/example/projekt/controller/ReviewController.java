package com.example.projekt.controller;

import com.example.projekt.model.Demand;
import com.example.projekt.model.Offer;
import com.example.projekt.model.Review;
import com.example.projekt.model.User;
import com.example.projekt.service.ReviewService;
import com.example.projekt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @PostMapping("/save")
    public String saveReview(@Valid @ModelAttribute Review review,
                             @RequestParam(required = false) Long offerId,
                             @RequestParam(required = false) Long demandId,
                             Authentication authentication) {
        User user = userService.getUserByUsername(authentication.getName());
        review.setUser(user);

        if (offerId != null) {
            Offer offer = new Offer();
            offer.setId(offerId);
            review.setOffer(offer);
        } else if (demandId != null) {
            Demand demand = new Demand();
            demand.setId(demandId);
            review.setDemand(demand);
        } else {
            throw new IllegalArgumentException("Either offerId or demandId must be provided.");
        }

        reviewService.saveReview(review);
        return offerId != null ? "redirect:/offers/detail/" + offerId : "redirect:/demands/detail/" + demandId;
    }

}
