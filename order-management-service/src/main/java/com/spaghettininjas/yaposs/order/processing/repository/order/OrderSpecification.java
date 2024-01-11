package com.spaghettininjas.yaposs.order.processing.repository.order;

import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class OrderSpecification {
    private static final String DATE_TIME_PROPERTY = "startDateTimeGMT";
    private static final String STAFF_USER_ID_PROPERTY = "staffUserId";
    private OrderSpecification() {}

    public static Specification<Order> dateTimeLessThan(Date dateTimeTillGMT) {
        return (root, query, builder) -> builder.lessThan(root.get(DATE_TIME_PROPERTY), dateTimeTillGMT);
    }

    public static Specification<Order> dateTimeGreaterThan(Date dateTimeFromGMT) {
        return (root, query, builder) -> builder.greaterThan(root.get(DATE_TIME_PROPERTY), dateTimeFromGMT);
    }

    public static Specification<Order> staffUserIdEqual(Long staffUserId) {
        return (root, query, builder) -> builder.equal(root.get(STAFF_USER_ID_PROPERTY), staffUserId);
    }
}
