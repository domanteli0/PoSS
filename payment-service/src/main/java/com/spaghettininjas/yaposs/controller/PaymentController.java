package com.spaghettininjas.yaposs.controller;

import com.spaghettininjas.yaposs.repository.entity.Payment;
import com.spaghettininjas.yaposs.service.PaymentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Payments")
public class PaymentController {

    private final PaymentsService service;

    public PaymentController(PaymentsService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Payment> getById(@PathVariable int id) {

        return service.findById((long) id)
                .map(payment -> ResponseEntity.ok().body(payment))
                .orElse(ResponseEntity.ok().body(null));
    }

    @GetMapping
    public ResponseEntity<Iterable<Payment>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                      @RequestParam(required = false) String name,
                                                      @RequestParam(required = false) String email) {
        Iterable<Payment> payment = service.findAll(page, pageSize, name, email);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteById((long) id);
    }

    @PostMapping
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(payment));
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Payment> addOrUpdatePayment(
        @PathVariable int id
//        @RequestBody PaymentDTO dto
    ) {
      return null;
    }
}
