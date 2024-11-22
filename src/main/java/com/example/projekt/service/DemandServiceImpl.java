package com.example.projekt.service;

import com.example.projekt.model.Demand;  // Use Demand instead of Offer
import com.example.projekt.model.Offer;
import com.example.projekt.repository.DemandRepository;  // Use DemandRepository instead of OfferRepository
import com.example.projekt.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandServiceImpl implements DemandService {  // Implement DemandService

    private final DemandRepository demandRepository;  // Use DemandRepository to interact with the Demand entity

    @Autowired
    public DemandServiceImpl(DemandRepository demandRepository) {  // Constructor injection for DemandRepository
        this.demandRepository = demandRepository;
    }

    @Override
    public List<Demand> getAllDemand() {  // Return a list of all demands
        return demandRepository.findAll();
    }

    @Override
    public Demand getDemandById(long id) {  // Get a demand by its ID
        return demandRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDemand(Demand demand) {  // Save or update a demand (treated like an offer)
        demandRepository.save(demand);
    }

    @Override
    public void deleteDemand(long id) {  // Delete a demand by its ID
        Optional<Demand> demand = demandRepository.findById(id);
        if (demand.isPresent()) {
            demandRepository.delete(demand.get());
        }
    }
}
