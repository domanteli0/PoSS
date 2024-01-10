package com.spaghettininjas.yaposs.appointment.processing.repository;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class AppointmentSpecification {
    private AppointmentSpecification() {}

    public static Specification<Appointment> dateTimeLessThan(LocalDateTime dateTimeTillGMT) {
        return (root, query, builder) -> builder.lessThan(root.get("dateTime"), dateTimeTillGMT);
    }

    public static Specification<Appointment> dateTimeGreaterThan(LocalDateTime dateTimeFromGMT) {
        return (root, query, builder) -> builder.greaterThan(root.get("dateTime"), dateTimeFromGMT);
    }
}
