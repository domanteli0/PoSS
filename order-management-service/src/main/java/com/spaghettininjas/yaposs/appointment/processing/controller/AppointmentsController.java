package com.spaghettininjas.yaposs.appointment.processing.controller;

import com.spaghettininjas.yaposs.appointment.processing.repository.AppointmentMapper;
import com.spaghettininjas.yaposs.appointment.processing.repository.Appointment;
import com.spaghettininjas.yaposs.appointment.processing.repository.AppointmentDTO;
import com.spaghettininjas.yaposs.appointment.processing.service.AppointmentService;
import com.spaghettininjas.yaposs.order.processing.repository.order.Order;
import com.spaghettininjas.yaposs.order.processing.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("/api/Appointments")
public class AppointmentsController {

    private final AppointmentService service;

    private final OrderService orderService;

    private final AppointmentMapper mapper;

    public AppointmentsController(AppointmentService service, AppointmentMapper mapper, OrderService orderService) {
        this.mapper = mapper;
        this.service = service;
        this.orderService = orderService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable long id) {
        return service.findById(id)
                .map(appointment -> ResponseEntity.ok().body(appointment))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Appointment>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false) Date fromDateTimeGMT,
                                                   @RequestParam(required = false) Date tillDateTimeGMT) {
        Iterable<Appointment> appointments = service.findAll(page, pageSize, fromDateTimeGMT, tillDateTimeGMT);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable long id){
        service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Appointment> add(@RequestBody Appointment appointment) {
        if (appointment.getDateTimeGMT() == null) {
            appointment.setDateTimeGMT(Date.from(Instant.now()));
        }
        // create Order in db first before Appointment
        Order order = appointment.getOrder();
        // have to provide Order date-time
        if (order.getDateTimeGMT() == null) {
            return ResponseEntity.badRequest().build();
        }
        order.setId(null);
        orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(appointment));
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Appointment> addOrUpdate(
        @PathVariable long id,
        @RequestBody AppointmentDTO dto
    ) {
      dto.setId(id);
      return service.findById(id)
            .map(value -> mapper.mergeWithDto(value, dto))
            .map(service::save)
            .map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
