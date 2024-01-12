package com.spaghettininjas.yaposs.order.processing.controller;

import com.spaghettininjas.yaposs.enums.OrderStatusEnum;
import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItem;
import com.spaghettininjas.yaposs.order.processing.repository.order.Order;
import com.spaghettininjas.yaposs.order.processing.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;


@RestController
@RequestMapping("/api/Orders")
public class OrdersController {

    private final OrderService service;

    public OrdersController(OrderService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(order -> ResponseEntity.ok().body(order))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date fromDateTimeGMT,
                                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date tillDateTimeGMT,
                                                   @RequestParam(required = false) Long staffUserId
                                                   ) {
        Iterable<Order> orders = service.findAll(page, pageSize, fromDateTimeGMT, tillDateTimeGMT, staffUserId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        if (order.getStartDateTimeGMT() == null) {
            order.setStartDateTimeGMT(Date.from(Instant.now()));
        }
        // generate id and set it in items to reference order
        order.generateId();
        order.getItems().forEach(item -> item.setOrder(order));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(order));
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Order> addOrUpdate(
        @PathVariable Long id,
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
                // new ids for items will be assigned in OrderItem db
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
        if (order.getStatus() != null) {
            existingOrder.setStatus(order.getStatus());
        }
        if (order.getStartDateTimeGMT() != null) {
            existingOrder.setStartDateTimeGMT(order.getStartDateTimeGMT());
        }
        existingOrder.addItems(itemsToAdd);
        return ResponseEntity.ok().body(service.save(existingOrder));
    }

    @PutMapping(path = "/status/{id}")
    ResponseEntity<Map<String, String>> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatusEnum status
            ) {
        Optional<Order> queriedOrder = service.findById(id);
        if (queriedOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Order existingOrder = queriedOrder.get();
        existingOrder.setStatus(status);
        Map<String, String> result = new HashMap<>();
        result.put("orderId", id.toString());
        result.put("newStatus", status.name());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(path = "/totalPrice/{id}")
    ResponseEntity<Double> getTotalPrice(
            @PathVariable Long id
    ) {
        Optional<Order> queriedOrder = service.findById(id);
        if (queriedOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Order existingOrder = queriedOrder.get();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem item : existingOrder.getItems()) {
            totalPrice = totalPrice
                    .add(BigDecimal.valueOf(item.getPriceOfUnit())
                            .multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return ResponseEntity.ok().body(totalPrice.doubleValue());
    }
}