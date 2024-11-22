package com.example.projekt.service;

import com.example.projekt.model.Offer;
import com.example.projekt.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Offer getOfferById(long id) {
        return offerRepository.findById(id).orElse(null);
    }

    @Override
    public void saveOffer(Offer offer) {
        offerRepository.save(offer);  // Save or update the offer in the database
    }
    @Override
    public void deleteOffer(long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            offerRepository.delete(offer.get());
        }
    }


}
