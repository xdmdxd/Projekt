package com.example.projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Lombok will generate a getter for this field

    @NotBlank
    private String text; // Review text

    @Min(0)
    @Max(5)
    private int stars; // Stars from 0 to 5

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User who created the review

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = true)
    private Offer offer; // Associated offer (nullable for reviews on demands)

    @ManyToOne
    @JoinColumn(name = "demand_id", nullable = true)
    private Demand demand; // Associated demand (nullable for reviews on offers)
}
