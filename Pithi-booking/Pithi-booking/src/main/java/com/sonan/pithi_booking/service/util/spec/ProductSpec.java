package com.sonan.pithi_booking.service.util.spec;

import com.sonan.pithi_booking.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductSpec implements Specification<Product> {

    private final ProductFilter productFilter;

    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Product> productRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (productFilter.getName() != null && !productFilter.getName().isEmpty()) {
            predicates.add(cb.like(cb.lower(productRoot.get("name")), "%" + productFilter.getName().toLowerCase() + "%"));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
