package com.spaghettininjas.yaposs.order.processing.repository.item;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class OrderItemSpecification {
    private OrderItemSpecification() {}

    public static Specification<OrderItem> priceLessThan(BigDecimal priceCeiling) {
        return (root, query, builder) -> builder.lessThan(root.get("price"), priceCeiling);
    }

    public static Specification<OrderItem> priceGreaterThan(BigDecimal priceFloor) {
        return (root, query, builder) -> builder.greaterThan(root.get("price"), priceFloor);
    }
}