package com.example.projekt.repository;

import com.example.projekt.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Modifying
    @Transactional
    void deleteByOfferId(Long offerId);

    @Modifying
    @Transactional
    void deleteByDemandId(Long demandId);
}
