package com.spaghettininjas.yaposs.appointment.processing.repository;

import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class AppointmentSpecification {
    private static final String DATE_TIME_PROPERTY = "endDateTimeGMT";
    private static final String STAFF_USER_ID_PROPERTY = "staffUserId";
    private AppointmentSpecification() {}

    public static Specification<Appointment> dateTimeLessThan(Date dateTimeTillGMT) {
        return (root, query, builder) -> builder.lessThan(root.get(DATE_TIME_PROPERTY), dateTimeTillGMT);
    }

    public static Specification<Appointment> dateTimeGreaterThan(Date dateTimeFromGMT) {
        return (root, query, builder) -> builder.greaterThan(root.get(DATE_TIME_PROPERTY), dateTimeFromGMT);
    }

    public static Specification<Appointment> staffUserIdEqual(Long staffUserId) {
        return (root, query, builder) -> builder.equal(root.get("order").get(STAFF_USER_ID_PROPERTY), staffUserId);
    }
}
