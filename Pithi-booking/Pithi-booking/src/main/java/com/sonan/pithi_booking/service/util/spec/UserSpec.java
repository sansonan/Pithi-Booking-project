package com.sonan.pithi_booking.service.util.spec;

import com.sonan.pithi_booking.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


@Data
public class UserSpec implements Specification<User> {
    private final UserFilter userFilter;

    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<User> userRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (userFilter.getUsername() != null && !userFilter.getUsername().isEmpty()) {
            predicates.add(cb.like(userRoot.get("username"), userFilter.getUsername()));
            predicates.add(cb.like(cb.lower(userRoot.get("username")), "%" + userFilter.getUsername().toLowerCase() + "%"));
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
