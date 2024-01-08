package com.spaghettininjas.yaposs.appointment.processing.repository.specification;

import com.spaghettininjas.yaposs.appointment.processing.repository.entity.appointment.Appointment;
import org.springframework.data.jpa.domain.Specification;

public class AppointmentSpecification {
    private AppointmentSpecification() {}

    public static Specification<Appointment> nameLike(String nameLike) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + nameLike + "%");
    }

    public static Specification<Appointment> emailLike(String emailLike) {
        return (root, query, builder) -> builder.like(root.get("email"), "%" + emailLike + "%");
    }
}
