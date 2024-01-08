package com.spaghettininjas.yaposs.repository.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Data
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
