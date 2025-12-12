package com.spring.knowhub.domain.repositories.report;

import com.spring.knowhub.domain.models.report.Report;

import java.util.Optional;

public interface ReportCommandRepository {
    Optional<Report> save(Report report);
    void deleteById(Long id);
}
