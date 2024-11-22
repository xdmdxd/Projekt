package com.example.projekt.service;

import com.example.projekt.model.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> getAllOffers();
    Offer getOfferById(long id); // Method to find an offer by its ID
    void saveOffer(Offer offer); // Method to save or update an offer
    void deleteOffer(long id);
}
