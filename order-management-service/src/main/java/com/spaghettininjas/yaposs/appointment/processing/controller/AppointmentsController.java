package com.spaghettininjas.yaposs.appointment.processing.controller;

import com.spaghettininjas.yaposs.appointment.processing.repository.AppointmentMapper;
import com.spaghettininjas.yaposs.appointment.processing.repository.Appointment;
import com.spaghettininjas.yaposs.appointment.processing.repository.AppointmentDTO;
import com.spaghettininjas.yaposs.appointment.processing.service.AppointmentService;
import com.spaghettininjas.yaposs.order.processing.repository.order.Order;
import com.spaghettininjas.yaposs.order.processing.service.OrderService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(appointment -> ResponseEntity.ok().body(appointment))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Appointment>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date fromDateTimeGMT,
                                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date tillDateTimeGMT,
                                                   @RequestParam(required = false) Long staffUserId) {
        Iterable<Appointment> appointments = service.findAll(page, pageSize, fromDateTimeGMT, tillDateTimeGMT, staffUserId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Appointment> add(@RequestBody Appointment appointment) {
        Order order = appointment.getOrder();
        if (appointment.getEndDateTimeGMT() == null && order.getStartDateTimeGMT() == null) {
            // times of appointment were not provided
            return ResponseEntity.badRequest().build();
        }
        // include start time in search
        Date adjustedStartDateTimeGMT = DateUtils.addMinutes(order.getStartDateTimeGMT(), -1);
        // search for other appointment START times in the appointed window
        Iterable<Order> takenStartOrders = orderService.findAll(0, 2,
                adjustedStartDateTimeGMT, appointment.getEndDateTimeGMT(), order.getStaffUserId());
        if (takenStartOrders.iterator().hasNext()) {
            // staff user is occupied at this time
            return ResponseEntity.badRequest().build();
        }
        // search for other appointment END times in the appointed window
        Iterable<Appointment> takenEndOrders = service.findAll(0, 2,
                adjustedStartDateTimeGMT, appointment.getEndDateTimeGMT(), order.getStaffUserId());
        if (takenEndOrders.iterator().hasNext()) {
            // staff user is occupied at this time
            return ResponseEntity.badRequest().build();
        }
        // create Order in db first before Appointment
        order.generateId();
        order.getItems().forEach(item -> item.setOrder(order));
        orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(appointment));
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Appointment> addOrUpdate(
        @PathVariable Long id,
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
