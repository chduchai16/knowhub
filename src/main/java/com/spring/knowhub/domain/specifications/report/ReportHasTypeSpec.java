package com.spring.knowhub.domain.specifications.report;

import com.spring.knowhub.domain.enums.report.ReportType;
import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.domain.specifications.Specification;

public class ReportHasTypeSpec implements Specification<Report> {
    private final ReportType type;

    public ReportHasTypeSpec(ReportType type) {
        this.type = type;
    }

    @Override
    public boolean isSatisfiedBy(Report report) {
        if (type == null) {
            return true;
        }
        return report.getReportType() == type;
    }

    public ReportType getType() {
        return type;
    }
}
