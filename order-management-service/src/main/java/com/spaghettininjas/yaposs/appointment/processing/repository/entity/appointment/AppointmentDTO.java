package com.spaghettininjas.yaposs.appointment.processing.repository.entity.appointment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentDTO {
    private Long id;

    private Long customerId;

    private Long staffUserId;

    private LocalDateTime dateTime;

    private String status;
}
