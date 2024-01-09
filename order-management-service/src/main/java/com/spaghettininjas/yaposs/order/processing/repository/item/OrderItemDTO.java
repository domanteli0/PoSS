package com.spaghettininjas.yaposs.order.processing.repository.item;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemDTO {
    private Long id;

    private Long orderId;

    private String name;

    private Integer quantity;

    private BigDecimal price;
}
