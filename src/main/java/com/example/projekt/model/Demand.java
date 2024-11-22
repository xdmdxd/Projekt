package com.example.projekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // The user who created the demand

    // Getters and setters for the user and other properties

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
