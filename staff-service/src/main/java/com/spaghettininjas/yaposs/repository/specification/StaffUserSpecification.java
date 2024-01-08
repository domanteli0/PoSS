package com.spaghettininjas.yaposs.repository.specification;

import com.spaghettininjas.yaposs.repository.entity.StaffUser;
import org.springframework.data.jpa.domain.Specification;

public class StaffUserSpecification {
    private StaffUserSpecification() {}

    public static Specification<StaffUser> nameLike(String nameLike) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + nameLike + "%");
    }

    public static Specification<StaffUser> emailLike(String emailLike) {
        return (root, query, builder) -> builder.like(root.get("email"), "%" + emailLike + "%");
    }
}
