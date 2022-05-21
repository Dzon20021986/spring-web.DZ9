package com.geekbrains.spring.web.repositories.specification;

import com.geekbrains.spring.web.data.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> costGreaterOrElseThan(Integer cost) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost)));
    }

    public static Specification<Product> lessThanOrEqualTo(Integer cost) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost)));
    }

    public static Specification<Product> likeTitle(String titlePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
    }
}
