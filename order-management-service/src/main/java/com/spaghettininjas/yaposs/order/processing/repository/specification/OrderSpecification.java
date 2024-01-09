package com.spaghettininjas.yaposs.order.processing.repository.specification;

import com.spaghettininjas.yaposs.order.processing.repository.entity.order.Order;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class OrderSpecification {
    private OrderSpecification() {}

    public static Specification<Order> priceLessThan(BigDecimal priceCeiling) {
        return (root, query, builder) -> builder.lessThan(root.get("price"), priceCeiling);
    }

    public static Specification<Order> priceGreaterThan(BigDecimal priceFloor) {
        return (root, query, builder) -> builder.greaterThan(root.get("price"), priceFloor);
    }
}
