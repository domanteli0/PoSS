package com.spaghettininjas.yaposs.appointment.processing.repository.specification;

import com.spaghettininjas.yaposs.appointment.processing.repository.entity.appointment.Appointment;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;

public class AppointmentSpecification {
    private AppointmentSpecification() {}

    public static Specification<Appointment> dateTimeLessThan(ZonedDateTime dateTimeTill) {
        return (root, query, builder) -> builder.lessThan(root.get("dateTime"), dateTimeTill);
    }

    public static Specification<Appointment> dateTimeGreaterThan(ZonedDateTime dateTimeFrom) {
        return (root, query, builder) -> builder.greaterThan(root.get("email"), dateTimeFrom);
    }
}
