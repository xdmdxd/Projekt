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
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title; // Description of the offer

    @NotBlank
    private String description; // Description of the offer

    @NotBlank
    private String price; // Description of the offer

    private LocalDateTime createdAt = LocalDateTime.now(); // Offer creation timestamp
    private String location; // Kde se proda

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // The user creating the offer
    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();


    // Getters and setters


}
