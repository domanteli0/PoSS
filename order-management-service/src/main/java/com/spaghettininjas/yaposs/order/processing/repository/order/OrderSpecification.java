package com.spaghettininjas.yaposs.order.processing.repository.order;

import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class OrderSpecification {
    private OrderSpecification() {}

    public static Specification<Order> dateTimeLessThan(Date dateTimeTillGMT) {
        return (root, query, builder) -> builder.lessThan(root.get("dateTimeGMT"), dateTimeTillGMT);
    }

    public static Specification<Order> dateTimeGreaterThan(Date dateTimeFromGMT) {
        return (root, query, builder) -> builder.greaterThan(root.get("dateTimeGMT"), dateTimeFromGMT);
    }
}
