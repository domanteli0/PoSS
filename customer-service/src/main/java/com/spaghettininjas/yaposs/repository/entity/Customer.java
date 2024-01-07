package com.spaghettininjas.yaposs.repository.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private  String loyaltyLevel;

    private int loyaltyPoints;

    private String name;

    public Customer(String email, String loyaltyLevel, int loyaltyPoints, String name) {
        this.email = email;
        this.loyaltyLevel = loyaltyLevel;
        this.loyaltyPoints = loyaltyPoints;
        this.name = name;
    }
    public Customer() {
    }
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLoyaltyLevel() {
        return loyaltyLevel;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public String getName() {
        return name;
    }
}
