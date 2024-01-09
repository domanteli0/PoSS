package com.spaghettininjas.yaposs.repository.specification;

import com.spaghettininjas.yaposs.repository.entity.Payment;
import org.springframework.data.jpa.domain.Specification;

public class PaymentSpecification {
    private PaymentSpecification() {}

    public static Specification<Payment> nameLike(String nameLike) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + nameLike + "%");
    }

    public static Specification<Payment> emailLike(String emailLike) {
        return (root, query, builder) -> builder.like(root.get("email"), "%" + emailLike + "%");
    }
}
