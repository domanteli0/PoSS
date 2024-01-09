package com.spaghettininjas.yaposs.appointment.processing.controller;

import com.spaghettininjas.yaposs.appointment.processing.repository.AppointmentMapper;
import com.spaghettininjas.yaposs.appointment.processing.repository.Appointment;
import com.spaghettininjas.yaposs.appointment.processing.repository.AppointmentDTO;
import com.spaghettininjas.yaposs.appointment.processing.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/Appointments")
public class AppointmentsController {

    private final AppointmentService service;
    private final AppointmentMapper mapper;

    public AppointmentsController(AppointmentService service, AppointmentMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable int id) {
        return service.findById((long) id)
                .map(appointment -> ResponseEntity.ok().body(appointment))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Appointment>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false) ZonedDateTime fromDateTimeGMT,
                                                   @RequestParam(required = false) ZonedDateTime tillDateTimeGMT) {
        Iterable<Appointment> appointments = service.findAll(page, pageSize, fromDateTimeGMT, tillDateTimeGMT);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteById((long) id);
    }

    @PostMapping
    public ResponseEntity<Appointment> addOrder(@RequestBody Appointment appointment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(appointment));
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Appointment> addOrUpdateOrder(
        @PathVariable int id,
        @RequestBody AppointmentDTO dto
    ) {
      dto.setId((long) id);
      return service.findById((long) id)
            .map(value -> mapper.mergeWithDto(value, dto))
            .map(service::save)
            .map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
