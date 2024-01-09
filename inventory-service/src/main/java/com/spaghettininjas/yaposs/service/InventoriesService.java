package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.repository.InventoryRepository;
import com.spaghettininjas.yaposs.repository.ProductRepository;
import com.spaghettininjas.yaposs.repository.entity.Inventory;
import com.spaghettininjas.yaposs.repository.entity.InventoryDTO;
import com.spaghettininjas.yaposs.repository.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Inventory save(InventoryDTO inventory) {
        Long productId = inventory.getProduct_id();



        Inventory newInventory = new Inventory();
        newInventory.setStockQuantity(inventory.getStockQuantity());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    System.out.println("Product not found for productId: " + productId);
                    return new RuntimeException("Product not found");
                });
        newInventory.setProduct(product);

        return inventoryRepository.save(newInventory);
    }

    public Iterable<Inventory> findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        return inventoryRepository.findAll(pageable).getContent();
    }
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

}
