package com.spaghettininjas.yaposs.service;

import com.spaghettininjas.yaposs.repository.CustomerRepository;
import com.spaghettininjas.yaposs.repository.entity.Customer;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.spaghettininjas.yaposs.repository.specification.CustomerSpecification.emailLike;
import static com.spaghettininjas.yaposs.repository.specification.CustomerSpecification.nameLike;

@Service
public class CustomersService {
    public final CustomerRepository repository;

    public CustomersService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Optional<Customer> findById(Long id){
        return repository.findById(id);
    }

    public Iterable<Customer> findAll(Integer page, Integer pageSize, @Nullable String name, @Nullable String email) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "name");
        Specification<Customer> filters = Specification.where(StringUtils.isBlank(name) ? null : nameLike(name))
                .and(StringUtils.isBlank(email) ? null : emailLike(email));
        return repository.findAll(filters, pageable).getContent();
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
