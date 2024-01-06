package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.repository.entity.Customer;
import com.spaghettininjas.yaposs.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class CustomersService {
    public CustomerRepository repository;

    public Optional<Customer> findById(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
