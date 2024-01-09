package com.spaghettininjas.yaposs.order.processing.repository.entity.item;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class ItemDTO {
    private Long id;

    private String name;

    private Integer quantity;

    private BigDecimal price;
}
