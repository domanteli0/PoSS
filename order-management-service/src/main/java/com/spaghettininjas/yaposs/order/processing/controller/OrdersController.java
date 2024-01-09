package com.spaghettininjas.yaposs.order.processing.controller;

import com.spaghettininjas.yaposs.appointment.processing.repository.Appointment;
import com.spaghettininjas.yaposs.appointment.processing.service.AppointmentService;
import com.spaghettininjas.yaposs.order.processing.repository.order.Order;
import com.spaghettininjas.yaposs.order.processing.service.OrderService;
import com.spaghettininjas.yaposs.order.processing.repository.order.OrderMapper;
import com.spaghettininjas.yaposs.order.processing.repository.order.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/Orders")
public class OrdersController {

    private final OrderService service;
    private final OrderMapper mapper;

    public OrdersController(OrderService service, OrderMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> getById(@PathVariable int id) {
        return service.findById((long) id)
                .map(order -> ResponseEntity.ok().body(order))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false) String dateTimeFromGMT,
                                                   @RequestParam(required = false) String dateTimeTillGMT) {
        Iterable<Order> orders = service.findAll(page, pageSize,
                ZonedDateTime.parse(dateTimeFromGMT), ZonedDateTime.parse(dateTimeTillGMT));
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteById((long) id);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        if (order.getDateTimeGMT() == null) {
            order.setDateTimeGMT(ZonedDateTime.now(ZoneId.of("GMT")).toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(order));
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Order> addOrUpdate(
        @PathVariable int id,
        @RequestBody OrderDTO dto
    ) {
      dto.setId((long) id);
      return service.findById((long) id)
            .map(value -> mapper.mergeWithDto(value, dto))
            .map(service::save)
            .map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
