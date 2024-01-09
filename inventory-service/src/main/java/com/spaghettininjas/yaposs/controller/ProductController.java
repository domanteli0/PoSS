package com.spaghettininjas.yaposs.controller;

import com.spaghettininjas.yaposs.repository.entity.Product;
import com.spaghettininjas.yaposs.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        return service.findById((long) id)
                .map(inventory -> ResponseEntity.ok().body(inventory))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteById((long) id);
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
            )
    {
        Iterable<Product> products = service.findAll(page, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product product
    ) {
        return service.findById((long)id)
                .map(p -> {
                    p.setPrice(product.getPrice());
                    p.setName(product.getName());
                    p.setCategory(product.getCategory());
                    return ResponseEntity.status(HttpStatus.OK).body(service.save(p));
                })
                .orElseGet(() -> {
                    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
                });

    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }


}
