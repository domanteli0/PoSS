package com.spaghettininjas.yaposs.order.processing.repository.specification;

import com.spaghettininjas.yaposs.order.processing.repository.entity.order.Order;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {
    private CustomerSpecification() {}

    public static Specification<Order> nameLike(String nameLike) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + nameLike + "%");
    }

    public static Specification<Order> emailLike(String emailLike) {
        return (root, query, builder) -> builder.like(root.get("email"), "%" + emailLike + "%");
    }
}
