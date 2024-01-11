package com.spaghettininjas.yaposs.appointment.processing.repository;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class AppointmentTimeDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startDateTimeGMT;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endDateTimeGMT;
}
