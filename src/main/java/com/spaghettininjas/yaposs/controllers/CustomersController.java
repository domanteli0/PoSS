package com.spaghettininjas.yaposs.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.spaghettininjas.yaposs.Domain.Customer;

import java.util.Arrays;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    Customer get(@PathVariable int id) {
        return Customer.newBuilder()
                .setId(id)
                .setLoyaltyLevel("EXAMPLE")
                .setLoyaltyPoints(0)
                .setName("Name Surname")
                .setEmail("example@example.com")
                .addAllFeedBack(Arrays.stream((new String[]{"Good", "Bad"})).toList())
                .build();
    }
}
