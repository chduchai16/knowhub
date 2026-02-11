package com.spring.knowhub.infrastructure.repositories.specifications.report;

import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.AndSpecification;
import com.spring.knowhub.domain.specifications.NotSpecification;
import com.spring.knowhub.domain.specifications.OrSpecification;
import com.spring.knowhub.domain.specifications.report.ReportHasKeywordSpec;
import com.spring.knowhub.domain.specifications.report.ReportHasStatusSpec;
import com.spring.knowhub.domain.specifications.report.ReportHasTypeSpec;
import com.spring.knowhub.infrastructure.entities.report.ReportEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ReportJpaSpecificationAdapter {
    private ReportJpaSpecificationAdapter() {
    }

    public static Specification<ReportEntity> toJpaSpecification(
            com.spring.knowhub.domain.specifications.Specification<Report> spec) {
        if (spec instanceof AlwaysTrueSpecification) {
            return (root, query, cb) -> cb.conjunction();
        }

        // AND
        if (spec instanceof AndSpecification<Report> andSpec) {
            return (root, query, cb) -> {
                Predicate left = toJpaSpecification(andSpec.getLeft()).toPredicate(root, query, cb);
                Predicate right = toJpaSpecification(andSpec.getRight()).toPredicate(root, query, cb);
                return cb.and(left, right);
            };
        }

        // OR
        if (spec instanceof OrSpecification<Report> orSpec) {
            return (root, query, cb) -> {
                Predicate left = toJpaSpecification(orSpec.getLeft()).toPredicate(root, query, cb);
                Predicate right = toJpaSpecification(orSpec.getRight()).toPredicate(root, query, cb);
                return cb.or(left, right);
            };
        }

        // NOT
        if (spec instanceof NotSpecification<Report> notSpec) {
            return (root, query, cb) -> cb.not(toJpaSpecification(notSpec.getWrapped()).toPredicate(root, query, cb));
        }

        // Keyword
        if (spec instanceof ReportHasKeywordSpec) {
            ReportHasKeywordSpec keywordSpec = (ReportHasKeywordSpec) spec;
            return (root, query, cb) -> cb.like(cb.lower(root.get("description")),
                    "%" + keywordSpec.getKeyword().toLowerCase() + "%");
        }

        // Type
        if (spec instanceof ReportHasTypeSpec) {
            ReportHasTypeSpec typeSpec = (ReportHasTypeSpec) spec;
            return (root, query, cb) -> cb.equal(root.get("reportType"), typeSpec.getType());
        }

        // Status
        if (spec instanceof ReportHasStatusSpec) {
            ReportHasStatusSpec statusSpec = (ReportHasStatusSpec) spec;
            return (root, query, cb) -> cb.equal(root.get("status"), statusSpec.getStatus());
        }

        throw new IllegalArgumentException("Không thể chuyển đổi Specification: " + spec.getClass().getName());
    }
}
