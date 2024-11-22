package com.example.projekt.service;

import com.example.projekt.model.Demand;
import com.example.projekt.model.Offer;

import java.util.List;

public interface DemandService {
    List<Demand> getAllDemand();
    Demand getDemandById(long id); // Method to find an offer by its ID
    void saveDemand(Demand demand); // Method to save or update an offer
    void deleteDemand(long id);
}
