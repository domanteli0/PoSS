package com.spaghettininjas.yaposs.order.processing.controller;


import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItem;
import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItemDTO;
import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItemMapper;
import com.spaghettininjas.yaposs.order.processing.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/OrderItems")
public class OrderItemsController {

    private final OrderItemService service;
    private final OrderItemMapper mapper;

    public OrderItemsController(OrderItemService service, OrderItemMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable int id) {
        return service.findById((long) id)
                .map(orderItem -> ResponseEntity.ok().body(orderItem))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<OrderItem>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false) BigDecimal priceFloor,
                                                   @RequestParam(required = false) BigDecimal priceCeiling) {
        Iterable<OrderItem> orderItems = service.findAll(page, pageSize, priceFloor, priceCeiling);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteById((long) id);
    }

    @PostMapping
    public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem orderItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(orderItem));
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<OrderItem> addOrUpdateOrderItem(
        @PathVariable int id,
        @RequestBody OrderItemDTO dto
    ) {
      dto.setId((long) id);
      return service.findById((long) id)
            .map(value -> mapper.mergeWithDto(value, dto))
            .map(service::save)
            .map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
