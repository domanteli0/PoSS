package com.spaghettininjas.yaposs.order.processing.service;

import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItem;
import com.spaghettininjas.yaposs.order.processing.repository.item.OrderItemRepository;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.spaghettininjas.yaposs.order.processing.repository.item.OrderItemSpecification.priceGreaterThan;
import static com.spaghettininjas.yaposs.order.processing.repository.item.OrderItemSpecification.priceLessThan;

@Service
public class OrderItemService {
    public final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public Optional<OrderItem> findById(Long id){
        return repository.findById(id);
    }

    public Iterable<OrderItem> findAll(Integer page, Integer pageSize, @Nullable BigDecimal priceFloor, @Nullable BigDecimal priceCeiling) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "name");
        Specification<OrderItem> filters = Specification.where(priceFloor == null ? null : priceGreaterThan(priceFloor))
                .and(priceCeiling == null ? null : priceLessThan(priceCeiling));
        return repository.findAll(filters, pageable).getContent();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public OrderItem save(OrderItem customer){
        return repository.save(customer);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }
}
