package com.spaghettininjas.yaposs.order.processing.repository.order;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderSpecification {
    private OrderSpecification() {}

    public static Specification<Order> dateTimeLessThan(LocalDateTime dateTimeTillGMT) {
        return (root, query, builder) -> builder.lessThan(root.get("dateTime"), dateTimeTillGMT);
    }

    public static Specification<Order> dateTimeGreaterThan(LocalDateTime dateTimeFromGMT) {
        return (root, query, builder) -> builder.greaterThan(root.get("dateTime"), dateTimeFromGMT);
    }
}
