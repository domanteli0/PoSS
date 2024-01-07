package com.spaghettininjas.yaposs.controller;

import com.spaghettininjas.yaposs.service.CustomersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spaghettininjas.yaposs.repository.entity.Customer;
import java.util.Arrays;

@RestController
@RequestMapping("/api/Customers")
public class CustomerController {

    private final CustomersService service;

    public CustomerController(CustomersService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> getById(@PathVariable int id) {
        return service.findById((long) id)
                .map(customer -> ResponseEntity.ok().body(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteById((long) id);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.save(customer));
    }
}
