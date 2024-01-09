package com.spaghettininjas.yaposs.appointment.processing.repository;

import com.spaghettininjas.yaposs.enums.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
public class AppointmentDTO {
    private Long id;

    private Long customerId;

    private Long orderId;

    private String dateTimeGMT;

    private Integer durationMinutes;

    private StatusEnum status;
}