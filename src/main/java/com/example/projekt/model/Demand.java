package com.example.projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "demands")
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title; // Description of the demand (e.g., what is needed)

    @NotBlank
    private String description; // Detailed description of the demand

    @NotBlank
    private String price; // Price or budget for the demand (can be negotiable)

    private LocalDateTime createdAt = LocalDateTime.now(); // Demand creation timestamp

    private String location; // Location where the demand is needed

    // Getters and Setters

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // The user who created the demand

    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    // Getters and setters for the user and other properties


}
