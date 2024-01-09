package com.spaghettininjas.yaposs.controller;

import com.spaghettininjas.yaposs.repository.entity.Inventory;
import com.spaghettininjas.yaposs.repository.entity.InventoryDTO;
import com.spaghettininjas.yaposs.repository.entity.Product;
import com.spaghettininjas.yaposs.service.InventoriesService;
import com.spaghettininjas.yaposs.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Inventory")
public class InventoryController {

    private final InventoriesService inventoriesService;
    private final ProductService productService;
    public InventoryController(InventoriesService inventoriesService, ProductService productService) {
        this.inventoriesService = inventoriesService;
        this.productService = productService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Inventory> getById(@PathVariable int id) {
        return inventoriesService.findById((long) id)
                .map(inventory -> ResponseEntity.ok().body(inventory))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable int id){
        inventoriesService.deleteById((long) id);
    }

    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody InventoryDTO inventory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoriesService.save(inventory));
    }

    @GetMapping
    public ResponseEntity<Iterable<Inventory>> findAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    )
    {
        Iterable<Inventory> products = inventoriesService.findAll(page, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<Inventory> updateInventory(
            @PathVariable int id,
            @RequestBody InventoryDTO inventory
    ) {
        return inventoriesService.findById((long)id)
                .map(in->{
                    in.setStockQuantity(inventory.getStockQuantity());
                    Product newP = productService.findById(inventory.getProduct_id()).orElse(null);
                    in.setProduct(newP);
                    return ResponseEntity.status(HttpStatus.OK).body(inventoriesService.save(in));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

}
