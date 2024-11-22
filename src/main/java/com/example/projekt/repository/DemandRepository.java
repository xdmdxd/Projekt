package com.example.projekt.repository;

import com.example.projekt.model.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandRepository extends JpaRepository<Demand, Long> {
}
