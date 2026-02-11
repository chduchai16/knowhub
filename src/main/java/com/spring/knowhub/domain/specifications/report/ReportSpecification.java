package com.spring.knowhub.domain.specifications.report;

import com.spring.knowhub.domain.enums.report.ReportStatus;
import com.spring.knowhub.domain.enums.report.ReportType;
import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;

public class ReportSpecification {

    public static Specification<Report> hasKeyword(String keyword) {
        return (keyword == null || keyword.isBlank())
                ? new AlwaysTrueSpecification<>()
                : new ReportHasKeywordSpec(keyword);
    }

    public static Specification<Report> hasType(ReportType type) {
        return type == null
                ? new AlwaysTrueSpecification<>()
                : new ReportHasTypeSpec(type);
    }

    public static Specification<Report> hasStatus(ReportStatus status) {
        return status == null
                ? new AlwaysTrueSpecification<>()
                : new ReportHasStatusSpec(status);
    }
}
