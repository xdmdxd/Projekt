package com.example.projekt.service;

import com.example.projekt.model.Offer;
import com.example.projekt.repository.OfferRepository;
import com.example.projekt.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ReviewRepository reviewRepository) {
        this.offerRepository = offerRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Offer getOfferById(long id) {
        return offerRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveOffer(Offer offer) {
        if (offer.getId() != 0) { // Check if it's an existing offer
            Offer existingOffer = offerRepository.findById(offer.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Offer not found"));

            // Preserve existing reviews
            offer.setReviews(existingOffer.getReviews());
        }

        // Save the updated or new offer
        offerRepository.save(offer);
    }


    @Override
    public void deleteOffer(long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            // Delete associated reviews
            reviewRepository.deleteByOfferId(id);

            // Delete the offer
            offerRepository.deleteById(id);
        }
    }
    public List<Offer> getOffersByUserId(Long userId) {
        return offerRepository.findByUser_Id(userId);
    }


}
