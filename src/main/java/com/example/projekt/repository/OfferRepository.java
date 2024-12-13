package com.example.projekt.repository;


import com.example.projekt.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByUser_Id(Long userId);
}
