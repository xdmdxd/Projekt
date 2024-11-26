package com.example.projekt.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data // Lombok annotations to auto-generate getters, setters, etc.
@Table(name = "users") // Specifies the table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating ID
    private Long id;

    @Column(nullable = false, unique = true) // Username must be unique
    private String username;

    @Column(nullable = false) // Password is required
    private String password;

    @Enumerated(EnumType.STRING) // The role will be stored as text (e.g. "ADMIN", "USER")
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, unique = true) // Email must be unique
    private String email; // New email field

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Demand> demands = new ArrayList<>();

}
