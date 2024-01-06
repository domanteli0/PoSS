package com.spaghettininjas.yaposs.controller;

import com.spaghettininjas.yaposs.service.CustomersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spaghettininjas.yaposs.repository.entity.Customer;
import java.util.Arrays;

@RestController
@RequestMapping("/api/Customers")
public class CustomerController {

    @Autowired
    private CustomersService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> get(@PathVariable int id) {
        return service.findById((long) id)
                .map(customer -> ResponseEntity.ok().body(customer))
                .orElse(ResponseEntity.ok().body(new Customer()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable int id){
        service.deleteById((long) id);
    }
}
