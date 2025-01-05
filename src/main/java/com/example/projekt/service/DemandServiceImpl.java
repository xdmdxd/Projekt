package com.example.projekt.service;

import com.example.projekt.model.Demand;  // Use Demand instead of Offer
import com.example.projekt.model.Offer;
import com.example.projekt.repository.DemandRepository;  // Use DemandRepository instead of OfferRepository
import com.example.projekt.repository.OfferRepository;
import com.example.projekt.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DemandServiceImpl implements DemandService {  // Implement DemandService

    private final DemandRepository demandRepository;  // Use DemandRepository to interact with the Demand entity
    private final ReviewRepository reviewRepository;

    @Autowired
    public DemandServiceImpl(DemandRepository demandRepository, ReviewRepository reviewRepository) {  // Constructor injection for DemandRepository
        this.demandRepository = demandRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Demand> getAllDemand() {  // Return a list of all demands
        return demandRepository.findAll();
    }

    @Override
    public Demand getDemandById(long id) {  // Get a demand by its ID
        return demandRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveDemand(Demand demand) {
        if (demand.getId() != 0) { // Check if it's an existing demand
            Demand existingDemand = demandRepository.findById(demand.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Demand not found"));

            // Preserve existing reviews
            demand.setReviews(existingDemand.getReviews());
        }

        // Save the updated or new demand
        demandRepository.save(demand);
    }


    @Override
    public void deleteDemand(long id) {  // Delete a demand by its ID
        Optional<Demand> demand = demandRepository.findById(id);
        if (demand.isPresent()) {
            reviewRepository.deleteByDemandId(id);
            demandRepository.delete(demand.get());
        }
    }
}
