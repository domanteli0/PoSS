package com.spaghettininjas.yaposs.repository.entity;

import lombok.*;

@Data
@Builder
public class CustomerDTO {
    private Long id;

    private String email;

    private  String loyaltyLevel;

    private Integer loyaltyPoints;

    private String name;
}
