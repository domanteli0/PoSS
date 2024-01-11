package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.repository.InventoryRepository;
import com.spaghettininjas.yaposs.repository.ProductRepository;
import com.spaghettininjas.yaposs.repository.entity.Inventory;
import com.spaghettininjas.yaposs.repository.entity.InventoryDTO;
import com.spaghettininjas.yaposs.repository.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class InventoriesService {
    public final InventoryRepository inventoryRepository;
    public final ProductRepository productRepository;

    public InventoriesService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public Optional<Inventory> findById(Long id){
        return inventoryRepository.findById(id);
    }

    public void deleteById(Long id){
        inventoryRepository.deleteById(id);
    }

    public Optional<Inventory> save(InventoryDTO inventory) {
        Long productId = inventory.getProductId();

        Inventory newInventory = new Inventory();
        newInventory.setStockQuantity(inventory.getStockQuantity());

        return productRepository.findById(productId).map(p -> {
            newInventory.setProduct(p);
            return inventoryRepository.save(newInventory);

        });

    }

    public Optional<Inventory> updateInventory(InventoryDTO inventory, Long inventoryId){
        return productRepository.findById(inventory.getProductId())
                .flatMap(newProduct -> this.findById(inventoryId).map(existingInventory -> {
            existingInventory.setStockQuantity(inventory.getStockQuantity());
            existingInventory.setProduct(newProduct);
            return inventoryRepository.save(existingInventory);
        }));
    }

    public Iterable<Inventory> findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        return inventoryRepository.findAll(pageable).getContent();
    }
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

}
