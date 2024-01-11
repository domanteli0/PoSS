package com.spaghettininjas.yaposs.appointment.processing.repository;

import com.spaghettininjas.yaposs.enums.StatusEnum;
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

    private StatusEnum status;
}
