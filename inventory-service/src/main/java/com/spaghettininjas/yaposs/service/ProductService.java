package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.repository.ProductRepository;
import com.spaghettininjas.yaposs.repository.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {
    public final ProductRepository repository;

    public ProductService( ProductRepository repository) {
        this.repository = repository;
    }

    public Optional<Product> findById(Long id){
        return repository.findById(id);
    }

    public Iterable<Product> findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        return repository.findAll(pageable).getContent();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Product save(Product product){
        return repository.save(product);
    }

}
