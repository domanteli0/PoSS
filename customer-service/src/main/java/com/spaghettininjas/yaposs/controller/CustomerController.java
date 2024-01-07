package com.spaghettininjas.yaposs.controller;

import com.spaghettininjas.yaposs.repository.entity.Customer;
import com.spaghettininjas.yaposs.service.CustomersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Iterable<Customer>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                      @RequestParam(required = false) String name,
                                                      @RequestParam(required = false) String email) {
        Iterable<Customer> customers = service.findAll(page, pageSize, name, email);
        return new ResponseEntity<>(customers, HttpStatus.OK);
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
