package com.spaghettininjas.yaposs.order.processing.repository.item;

import org.springframework.data.jpa.domain.Specification;

public class OrderItemSpecification {
    private OrderItemSpecification() {}

    public static Specification<OrderItem> priceLessThan(Double priceCeiling) {
        return (root, query, builder) -> builder.lessThan(root.get("priceOfUnit"), priceCeiling);
    }

    public static Specification<OrderItem> priceGreaterThan(Double priceFloor) {
        return (root, query, builder) -> builder.greaterThan(root.get("priceOfUnit"), priceFloor);
    }
}
