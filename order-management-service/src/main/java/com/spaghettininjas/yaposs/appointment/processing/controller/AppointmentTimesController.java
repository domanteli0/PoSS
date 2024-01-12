package com.spaghettininjas.yaposs.appointment.processing.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spaghettininjas.yaposs.appointment.processing.repository.Appointment;
import com.spaghettininjas.yaposs.appointment.processing.repository.AppointmentTimeDTO;
import com.spaghettininjas.yaposs.appointment.processing.service.AppointmentService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RequestMapping("/api/AppointmentTimes")
@Controller
public class AppointmentTimesController {

    private final AppointmentService service;

    public AppointmentTimesController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<AppointmentTimeDTO>> findAll(
        @RequestParam Long staffUserId, @RequestParam(required = false, defaultValue = "0") Integer page,

        @RequestParam(required = false, defaultValue = "10") Integer pageSize,

        @RequestParam(required = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        Date tillDateTimeGMT
    ) {
        Iterable<Appointment> appointments = service.findAll(page, pageSize, Date.from(Instant.now()), tillDateTimeGMT, staffUserId);
        Iterator<Appointment> appointmentIterator = appointments.iterator();
        List<AppointmentTimeDTO> appointmentTimeDTOS = new ArrayList<>();
        Appointment nextAppointment;
        while(appointmentIterator.hasNext()) {
            nextAppointment = appointmentIterator.next();
            Appointment currentAppointment = nextAppointment;
            if (appointmentIterator.hasNext()) {
                nextAppointment = appointmentIterator.next();
            }
            // get time windows between when staffUser is occupied
            AppointmentTimeDTO appointmentTimeDTO = AppointmentTimeDTO.builder()
                    .startDateTimeGMT(currentAppointment.getEndDateTimeGMT())
                    .endDateTimeGMT(nextAppointment.getOrder().getStartDateTimeGMT())
                    .build();
            appointmentTimeDTOS.add(appointmentTimeDTO);
        }
        return ResponseEntity.ok(appointmentTimeDTOS);
    }
}
