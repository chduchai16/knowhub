package com.spring.knowhub.infrastructure.repositories.specifications.user;

import com.spring.knowhub.domain.enums.user.UserStatus;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.specifications.*;
import com.spring.knowhub.domain.specifications.user.*;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public final class UserJpaSpecificationAdapter {

    // note : cơ chế đệ quy để chuyển đổi các Specification thành Specification JPA

    private UserJpaSpecificationAdapter() {}

    public static Specification<UserEntity> toJpaSpecification(com.spring.knowhub.domain.specifications.Specification<User> spec) {

        if (spec instanceof AlwaysTrueSpecification) {
            return (root, query, cb) -> cb.conjunction();
        }

        // AND
        if (spec instanceof AndSpecification<User> andSpec) {
            return (root, query, cb) -> {
                Predicate left = toJpaSpecification(andSpec.getLeft()).toPredicate(root, query, cb);
                Predicate right = toJpaSpecification(andSpec.getRight()).toPredicate(root, query, cb);
                return cb.and(left, right);
            };
        }

        // OR
        if (spec instanceof OrSpecification<User> orSpec) {
            return (root, query, cb) -> {
                Predicate left = toJpaSpecification(orSpec.getLeft()).toPredicate(root, query, cb);
                Predicate right = toJpaSpecification(orSpec.getRight()).toPredicate(root, query, cb);
                return cb.or(left, right);
            };
        }

        // NOT
        if (spec instanceof NotSpecification<User> notSpec) {
            return (root, query, cb) -> cb.not(toJpaSpecification(notSpec.getWrapped()).toPredicate(root, query, cb));
        }

        // USER IS ACTIVE
        if (spec instanceof UserIsActiveSpec) {
            return (root, query, cb) -> cb.equal(root.get("status"), UserStatus.ACTIVE);
        }

        if(spec instanceof UserHasStatusSpec) {
            UserHasStatusSpec statusSpec = (UserHasStatusSpec) spec;
            return (root, query, cb) -> cb.equal(root.get("status"), statusSpec.getStatus());
        }

        // USER HAS ROLE
        if (spec instanceof UserHasRoleSpec roleSpec) {
            return (root, query, cb) -> cb.equal(root.get("role").get("id"), roleSpec.getRoleId());
        }

        // USER KEYWORD
        if (spec instanceof UserSearchSpec searchSpec) {
            String keyword = searchSpec.getKeyword();

            if (keyword == null || keyword.isBlank()) {
                return (root, query, cb) -> cb.conjunction();
            }

            String likePattern = "%" + keyword.toLowerCase() + "%";

            return (root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("username")), likePattern),
                    cb.like(cb.lower(root.get("email")), likePattern),
                    cb.like(cb.lower(root.get("fullName")), likePattern)
            );
        }

        // has name

        if(spec instanceof UserHasNameSpec nameSpec) {
            String name = nameSpec.getName();
            String likePattern = "%" + name.toLowerCase() + "%";
            return (root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("fullName")), likePattern),
                    cb.like(cb.lower(root.get("username")), likePattern)
            );
        }
        throw new IllegalArgumentException("Không thể chuyển đổi Specification: " + spec.getClass().getName());
    }
}
