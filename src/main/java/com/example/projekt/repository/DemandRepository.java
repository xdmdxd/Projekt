package com.example.projekt.repository;

import com.example.projekt.model.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandRepository extends JpaRepository<Demand, Long> {
    List<Demand> findByUser_Id(Long userId);
}
