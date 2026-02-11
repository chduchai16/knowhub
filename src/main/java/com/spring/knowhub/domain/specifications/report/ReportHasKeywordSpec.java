package com.spring.knowhub.domain.specifications.report;

import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.domain.specifications.Specification;

public class ReportHasKeywordSpec implements Specification<Report> {
    private final String keyword;

    public ReportHasKeywordSpec(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isSatisfiedBy(Report report) {
        if (keyword == null || keyword.isBlank()) {
            return true;
        }
        return report.getDescription() != null && report.getDescription().toLowerCase().contains(keyword.toLowerCase());
    }

    public String getKeyword() {
        return keyword;
    }
}
