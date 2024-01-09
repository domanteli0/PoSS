package com.spaghettininjas.yaposs.repository.specification;

import com.spaghettininjas.yaposs.repository.entity.Inventory;
import org.springframework.data.jpa.domain.Specification;

public class InventorySpecification {

    private InventorySpecification() {}

    public static Specification<Inventory> productIdEqual(Long productId) {
        return (root, query, builder) -> builder.equal(root.get("product").get("id"), productId);
    }
}