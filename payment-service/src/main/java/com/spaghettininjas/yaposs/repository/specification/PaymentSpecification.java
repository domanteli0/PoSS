package com.spaghettininjas.yaposs.repository.specification;

import com.spaghettininjas.yaposs.entity.Transaction;
import org.springframework.data.jpa.domain.Specification;

public class PaymentSpecification {
    private PaymentSpecification() {}

    public static Specification<Transaction> orderIdEqual(Long orderId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("orderId"), orderId);
    }

    public static Specification<Transaction> staffUserIdEqual(Long staffUserId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("staffUserId"), staffUserId);
    }

    public static Specification<Transaction> paymentTypeLike(String paymentType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("paymentType"), "%" + paymentType + "%");
    }
}
