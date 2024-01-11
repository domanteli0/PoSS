package com.spaghettininjas.yaposs.appointment.processing.repository;

import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class AppointmentSpecification {
    private AppointmentSpecification() {}

    public static Specification<Appointment> dateTimeLessThan(Date dateTimeTillGMT) {
        return (root, query, builder) -> builder.lessThan(root.get("dateTimeGMT"), dateTimeTillGMT);
    }

    public static Specification<Appointment> dateTimeGreaterThan(Date dateTimeFromGMT) {
        return (root, query, builder) -> builder.greaterThan(root.get("dateTimeGMT"), dateTimeFromGMT);
    }
}
