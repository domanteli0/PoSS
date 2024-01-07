package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.repository.entity.Customer;
import com.spaghettininjas.yaposs.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomersService {
    public final CustomerRepository repository;

    public CustomersService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Optional<Customer> findById(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Customer save(Customer customer){
        return repository.save(customer);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }
}
