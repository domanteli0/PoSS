package com.spaghettininjas.yaposs.appointment.processing.repository.specification;

import com.spaghettininjas.yaposs.appointment.processing.repository.entity.appointment.Appointment;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;

public class AppointmentSpecification {
    private AppointmentSpecification() {}

    public static Specification<Appointment> dateTimeLessThan(ZonedDateTime dateTimeTillGMT) {
        return (root, query, builder) -> builder.lessThan(root.get("dateTime"), dateTimeTillGMT);
    }

    public static Specification<Appointment> dateTimeGreaterThan(ZonedDateTime dateTimeFromGMT) {
        return (root, query, builder) -> builder.greaterThan(root.get("email"), dateTimeFromGMT);
    }
}
