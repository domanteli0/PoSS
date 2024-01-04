package com.spaghettininjas.yaposs;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.spaghettininjas.yaposs.DTO.Customer;

import java.util.Arrays;

@RestController
@RequestMapping("/api/Customers")
public class Controller {

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
