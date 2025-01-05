package com.example.projekt.service;

import com.example.projekt.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByOfferId(Long offerId);
    List<Review> getReviewsByDemandId(Long demandId);
    void saveReview(Review review);
}
