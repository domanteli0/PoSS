package com.spaghettininjas.yaposs.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class OrderItem {
    private Long id;

    private Order order;

    private String name;

    private Integer quantity;

    private Double priceOfUnit;
}
