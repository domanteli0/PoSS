package com.spaghettininjas.yaposs.appointment.processing.repository.entity.appointment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
public class AppointmentDTO {
    private Long id;

    private Long customerId;

    private Long staffUserId;

    private ZonedDateTime dateTimeGMT;

    private String status;
}
