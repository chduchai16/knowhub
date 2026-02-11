package com.spring.knowhub.domain.specifications.report;

import com.spring.knowhub.domain.enums.report.ReportStatus;
import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.domain.specifications.Specification;

public class ReportHasStatusSpec implements Specification<Report> {
    private final ReportStatus status;

    public ReportHasStatusSpec(ReportStatus status) {
        this.status = status;
    }

    @Override
    public boolean isSatisfiedBy(Report report) {
        if (status == null) {
            return true;
        }
        return report.getStatus() == status;
    }

    public ReportStatus getStatus() {
        return status;
    }
}
