package com.spaghettininjas.yaposs.appointment.processing.repository;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AppointmentDTO {
    private Long id;

    private Long customerId;

    private Long orderId;

    private Date endDateTimeGMT;
}
