package com.spaghettininjas.yaposs.order.processing.controller;

import com.spaghettininjas.yaposs.appointment.processing.repository.Appointment;
import com.spaghettininjas.yaposs.appointment.processing.service.AppointmentService;
import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItem;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/Orders")
public class OrdersController {

    private final OrderService service;

    public OrdersController(OrderService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> getById(@PathVariable long id) {
        return service.findById(id)
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
    public void deleteById(@PathVariable long id){
        service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        if (order.getDateTimeGMT() == null) {
            order.setDateTimeGMT(ZonedDateTime.now(ZoneId.of("GMT")).toString());
        }
        // generate ids and set them to reference order in items
        order.setId(null);
        order.getItems().forEach(item -> item.setOrder(order));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(order));
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Order> addOrUpdate(
        @PathVariable long id,
        @RequestBody Order order
    ) {
        Optional<Order> queriedOrder = service.findById(id);
        if (queriedOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Order existingOrder = queriedOrder.get();
        List<OrderItem> itemsToAdd = new ArrayList<>() {
        };
        for (OrderItem item : order.getItems()) {
            if (item.getId() == null) {
                // new id will be assigned in OrderItem db
                item.setId(existingOrder.getId());
                item.setOrder(existingOrder);
                itemsToAdd.add(item);
            }
            for (OrderItem existingItem : existingOrder.getItems()) {
                if (existingItem.getId().equals(item.getId())) {
                    // update changes
                    existingItem.setQuantity(item.getQuantity());
                    existingItem.setPriceOfUnit(item.getPriceOfUnit());
                }
            }
        }
        existingOrder.addItems(itemsToAdd);
        return ResponseEntity.ok().body(service.save(existingOrder));
    }
}
