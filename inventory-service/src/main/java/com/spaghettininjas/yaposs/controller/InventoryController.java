package com.spaghettininjas.yaposs.controller;

import com.spaghettininjas.yaposs.repository.entity.Inventory;
import com.spaghettininjas.yaposs.service.InventoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Inventory")
public class InventoryController {

    private final InventoriesService service;

    public InventoryController(InventoriesService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Inventory> getById(@PathVariable int id) {
        return service.findById((long) id)
                .map(inventory -> ResponseEntity.ok().body(inventory))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteById((long) id);
    }

    @PostMapping
    public ResponseEntity<Inventory> addCustomer(@RequestBody Inventory inventory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inventory));
    }


}
