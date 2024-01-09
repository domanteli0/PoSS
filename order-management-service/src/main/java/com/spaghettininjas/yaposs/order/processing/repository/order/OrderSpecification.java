package com.spaghettininjas.yaposs.order.processing.repository.order;

import com.spaghettininjas.yaposs.appointment.processing.repository.Appointment;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class OrderSpecification {
    private OrderSpecification() {}

    public static Specification<Order> dateTimeLessThan(ZonedDateTime dateTimeTillGMT) {
        return (root, query, builder) -> builder.lessThan(root.get("dateTime"), dateTimeTillGMT);
    }

    public static Specification<Order> dateTimeGreaterThan(ZonedDateTime dateTimeFromGMT) {
        return (root, query, builder) -> builder.greaterThan(root.get("dateTime"), dateTimeFromGMT);
    }
}
