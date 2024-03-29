package com.spaghettininjas.yaposs.order.processing.service;

import com.spaghettininjas.yaposs.order.processing.repository.order.OrderRepository;
import com.spaghettininjas.yaposs.order.processing.repository.order.Order;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.spaghettininjas.yaposs.order.processing.repository.order.OrderSpecification.*;

@Service
public class OrderService {
    public final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Optional<Order> findById(Long id){
        return repository.findById(id);
    }

    public Iterable<Order> findAll(Integer page, Integer pageSize,
                                   @Nullable Date fromDateTimeGMT,
                                   @Nullable Date tillDateTimeGMT,
                                   @Nullable Long staffUserId) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "startDateTimeGMT");
        Specification<Order> filters = Specification.where(fromDateTimeGMT == null ? null : dateTimeGreaterThan(fromDateTimeGMT))
                .and(tillDateTimeGMT == null ? null : dateTimeLessThan(tillDateTimeGMT))
                .and(staffUserId == null ? null : staffUserIdEqual(staffUserId));
        return repository.findAll(filters, pageable).getContent();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Order save(Order order){
        return repository.save(order);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }
}
