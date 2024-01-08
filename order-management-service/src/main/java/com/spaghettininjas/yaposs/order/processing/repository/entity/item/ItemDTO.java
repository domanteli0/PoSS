package com.spaghettininjas.yaposs.order.processing.repository.entity.item;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class ItemDTO {
    private Long id;

    private String paymentType;

    private BigDecimal tax;

    private BigDecimal totalDiscount;

    private BigDecimal price;
}
