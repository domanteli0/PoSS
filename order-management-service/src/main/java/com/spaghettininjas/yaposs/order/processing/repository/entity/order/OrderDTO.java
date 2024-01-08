package com.spaghettininjas.yaposs.order.processing.repository.entity.order;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class OrderDTO {
    private Long id;

    private String paymentType;

    private BigDecimal tax;

    private BigDecimal totalDiscount;

    private BigDecimal price;
}
