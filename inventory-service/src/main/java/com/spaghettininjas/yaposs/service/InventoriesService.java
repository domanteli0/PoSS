package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.repository.InventoryRepository;
import com.spaghettininjas.yaposs.repository.entity.Inventory;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class InventoriesService {
    public final InventoryRepository repository;

    public InventoriesService(InventoryRepository repository) {
        this.repository = repository;
    }

    public Optional<Inventory> findById(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Inventory save(Inventory customer){
        return repository.save(customer);
    }

}
