package com.spring.knowhub.infrastructure.repositories.specifications.post;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.AndSpecification;
import com.spring.knowhub.domain.specifications.NotSpecification;
import com.spring.knowhub.domain.specifications.OrSpecification;
import com.spring.knowhub.domain.specifications.post.PostHasContentSpec;
import com.spring.knowhub.domain.specifications.post.PostHasStatusSpec;
import com.spring.knowhub.domain.specifications.post.PostHasUserNameSpec;
import com.spring.knowhub.infrastructure.entities.post.PostEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PostJpaSpecificationAdapter {
    private PostJpaSpecificationAdapter() {
    }

    public static Specification<PostEntity> toJpaSpecification(
            com.spring.knowhub.domain.specifications.Specification<Post> spec) {
        if (spec instanceof AlwaysTrueSpecification) {
            return (root, query, cb) -> cb.conjunction();
        }

        // AND
        if (spec instanceof AndSpecification<Post> andSpec) {
            return (root, query, cb) -> {
                Predicate left = toJpaSpecification(andSpec.getLeft()).toPredicate(root, query, cb);
                Predicate right = toJpaSpecification(andSpec.getRight()).toPredicate(root, query, cb);
                return cb.and(left, right);
            };
        }

        // OR
        if (spec instanceof OrSpecification<Post> orSpec) {
            return (root, query, cb) -> {
                Predicate left = toJpaSpecification(orSpec.getLeft()).toPredicate(root, query, cb);
                Predicate right = toJpaSpecification(orSpec.getRight()).toPredicate(root, query, cb);
                return cb.or(left, right);
            };
        }

        // NOT
        if (spec instanceof NotSpecification<Post> notSpec) {
            return (root, query, cb) -> cb.not(toJpaSpecification(notSpec.getWrapped()).toPredicate(root, query, cb));
        }

        // lọc bằng content
        if (spec instanceof PostHasContentSpec) {
            PostHasContentSpec contentSpec = (PostHasContentSpec) spec;
            return (root, query, cb) -> cb.like(root.get("content"), "%" + contentSpec.getKeyword() + "%");
        }

        // status
        if (spec instanceof PostHasStatusSpec) {
            PostHasStatusSpec statusSpec = (PostHasStatusSpec) spec;
            return (root, query, cb) -> cb.equal(root.get("status"), statusSpec.getStatus());
        }

        // user name
        if (spec instanceof PostHasUserNameSpec) {
            PostHasUserNameSpec userNameSpec = (PostHasUserNameSpec) spec;
            return (root, query, cb) -> cb.equal(root.get("user").get("username"), userNameSpec.getUserName());
        }

        throw new IllegalArgumentException("Không thể chuyển đổi Specification: " + spec.getClass().getName());
    }
}
