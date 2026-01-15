package com.spring.knowhub.infrastructure.repositories.specifications.media;

import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.AndSpecification;
import com.spring.knowhub.domain.specifications.NotSpecification;
import com.spring.knowhub.domain.specifications.OrSpecification;
import com.spring.knowhub.domain.specifications.media.MediaHasIdSpec;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerIdSpec;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerTypeSpec;
import com.spring.knowhub.infrastructure.entities.media.MediaEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class MediaJpaSpecificationAdapter {

    private MediaJpaSpecificationAdapter() {}

    public static Specification<MediaEntity> toJpaSpecification(
            com.spring.knowhub.domain.specifications.Specification<Media> spec
    ) {

        if (spec instanceof AlwaysTrueSpecification) {
            return (root, query, cb) -> cb.conjunction();
        }

        if (spec instanceof AndSpecification<Media> andSpec) {
            return (root, query, cb) -> {
                Predicate left = toJpaSpecification(andSpec.getLeft()).toPredicate(root, query, cb);
                Predicate right = toJpaSpecification(andSpec.getRight()).toPredicate(root, query, cb);
                return cb.and(left, right);
            };
        }

        if(spec instanceof OrSpecification<Media> orSpec) {
            return (root, query, cb) -> {
                Predicate left = toJpaSpecification(orSpec.getLeft()).toPredicate(root, query, cb);
                Predicate right = toJpaSpecification(orSpec.getRight()).toPredicate(root, query, cb);
                return cb.or(left, right);
            };
        }

        if(spec instanceof NotSpecification<Media> notSpec) {
            return (root, query, cb) -> cb.not(
                    toJpaSpecification(notSpec.getWrapped()).toPredicate(root, query, cb)
            );
        }

        if(spec instanceof MediaHasOwnerIdSpec) {
            MediaHasOwnerIdSpec ownerIdSpec = (MediaHasOwnerIdSpec) spec;
            return (root, query, cb) -> cb.equal(root.get("ownerId"), ownerIdSpec.getOwnerId());
        }

        if(spec instanceof MediaHasOwnerTypeSpec) {
            MediaHasOwnerTypeSpec ownerTypeSpec = (MediaHasOwnerTypeSpec) spec;
            return (root, query, cb) -> cb.equal(root.get("ownerType"), ownerTypeSpec.getOwnerType());
        }

        if(spec instanceof MediaHasIdSpec) {
            MediaHasIdSpec idSpec = (MediaHasIdSpec) spec;
            return (root, query, cb) -> cb.equal(root.get("id"), idSpec.getId());
        }

        throw new IllegalArgumentException(
                "Không thể chuyển đổi Media Specification: " + spec.getClass().getName()
        );
    }
}
