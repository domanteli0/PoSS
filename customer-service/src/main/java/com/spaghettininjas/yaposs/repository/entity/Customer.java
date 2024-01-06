package com.spaghettininjas.yaposs.repository.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "loyalty_level")
    private  String loyaltyLevel;

    @Column(name = "loyalty_points")
    private String loyaltyPoints;


    @Column(name = "name")
    private String name;

}
