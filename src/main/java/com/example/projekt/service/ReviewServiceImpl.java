package com.example.projekt.service;

import com.example.projekt.model.Review;
import com.example.projekt.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getReviewsByOfferId(Long offerId) {
        return reviewRepository.findAll().stream()
                .filter(review -> review.getOffer() != null && review.getOffer().getId() == offerId) // Use '==' for primitive comparison
                .toList();
    }

    @Override
    public List<Review> getReviewsByDemandId(Long demandId) {
        return reviewRepository.findAll().stream()
                .filter(review -> review.getDemand() != null && review.getDemand().getId() == demandId) // Use '==' for primitive comparison
                .toList();
    }


    @Override
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }
}
