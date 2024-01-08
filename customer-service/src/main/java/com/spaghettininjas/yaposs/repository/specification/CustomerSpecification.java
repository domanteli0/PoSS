package com.spaghettininjas.yaposs.repository.specification;

import com.spaghettininjas.yaposs.repository.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {
    private CustomerSpecification() {}

    public static Specification<Customer> nameLike(String nameLike) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + nameLike + "%");
    }

    public static Specification<Customer> emailLike(String emailLike) {
        return (root, query, builder) -> builder.like(root.get("email"), "%" + emailLike + "%");
    }
}
