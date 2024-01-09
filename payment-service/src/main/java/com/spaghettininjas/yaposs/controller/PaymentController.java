package com.spaghettininjas.yaposs.controller;

import com.spaghettininjas.yaposs.dto.TransactionUpdateRequest;
import com.spaghettininjas.yaposs.entity.Transaction;
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

    @GetMapping
    public ResponseEntity<Iterable<Transaction>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                         @RequestParam(required = false) String paymentType,
                                                         @RequestParam(required = false) Long staffUserId,
                                                         @RequestParam(required = false) Long orderId) {
        Iterable<Transaction> transactions = this.service.findAll(page, pageSize, paymentType, staffUserId, orderId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public Transaction getById(@PathVariable Long id) {
        return this.service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(transaction));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable Long id){
        this.service.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Transaction> updateTransaction(
            @PathVariable Long id,
            @RequestBody TransactionUpdateRequest transactionUpdateRequest
    ) {
        Transaction updatedTransaction = this.service.updateTransaction(id, transactionUpdateRequest);
        if(updatedTransaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedTransaction);
    }
}
