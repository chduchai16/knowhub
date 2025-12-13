package com.spring.knowhub.domain.repositories.report;

import com.spring.knowhub.domain.models.report.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReportRepository {
    Optional<Report> save(Report report);
    void deleteById(Long id);
    Optional<Report> findById(Long id);
    Page<Report> findReportsPaged(Pageable pageable);
}
