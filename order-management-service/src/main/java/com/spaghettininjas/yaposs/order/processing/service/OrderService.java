package com.spaghettininjas.yaposs.order.processing.service;

import com.spaghettininjas.yaposs.order.processing.repository.OrderRepository;
import com.spaghettininjas.yaposs.order.processing.repository.entity.order.Order;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.spaghettininjas.yaposs.order.processing.repository.specification.CustomerSpecification.emailLike;
import static com.spaghettininjas.yaposs.order.processing.repository.specification.CustomerSpecification.nameLike;

@Service
public class OrderService {
    public final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Optional<Order> findById(Long id){
        return repository.findById(id);
    }

    public Iterable<Order> findAll(Integer page, Integer pageSize, @Nullable String name, @Nullable String email) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "name");
        Specification<Order> filters = Specification.where(StringUtils.isBlank(name) ? null : nameLike(name))
                .and(StringUtils.isBlank(email) ? null : emailLike(email));
        return repository.findAll(filters, pageable).getContent();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Order save(Order customer){
        return repository.save(customer);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }
}
