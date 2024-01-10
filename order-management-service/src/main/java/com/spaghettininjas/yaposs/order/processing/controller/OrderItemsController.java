package com.spaghettininjas.yaposs.order.processing.controller;


import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItem;
import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItemDTO;
import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItemMapper;
import com.spaghettininjas.yaposs.order.processing.repository.order.Order;
import com.spaghettininjas.yaposs.order.processing.service.OrderItemService;
import com.spaghettininjas.yaposs.order.processing.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/OrderItems")
public class OrderItemsController {

    private final OrderItemService service;
    private final OrderService orderService;
    private final OrderItemMapper mapper;

    public OrderItemsController(OrderItemService service, OrderItemMapper mapper, OrderService orderService) {
        this.mapper = mapper;
        this.service = service;
        this.orderService = orderService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable long id) {
        return service.findById(id)
                .map(orderItem -> ResponseEntity.ok().body(orderItem))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<OrderItem>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false) Double priceFloor,
                                                   @RequestParam(required = false) Double priceCeiling) {
        Iterable<OrderItem> orderItems = service.findAll(page, pageSize, priceFloor, priceCeiling);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable long id){
        service.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<OrderItem> add(@RequestBody OrderItemDTO orderItemDTO) {
        if (orderItemDTO.getOrderId() == null) {
            return ResponseEntity.notFound().build();
        }
        Optional<Order> order = orderService.findById(orderItemDTO.getOrderId());
        if (order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        OrderItem orderItem = OrderItem.builder()
                .name(orderItemDTO.getName())
                .priceOfUnit(orderItemDTO.getPriceOfUnit())
                .quantity(orderItemDTO.getQuantity())
                .order(order.get())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(orderItem));
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<OrderItem> addOrUpdate(
        @PathVariable long id,
        @RequestBody OrderItemDTO dto
    ) {
      dto.setId(id);
      return service.findById(id)
            .map(value -> mapper.mergeWithDto(value, dto))
            .map(service::save)
            .map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
