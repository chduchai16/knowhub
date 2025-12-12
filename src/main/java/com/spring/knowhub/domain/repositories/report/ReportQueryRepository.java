package com.spring.knowhub.domain.repositories.report;

import com.spring.knowhub.domain.models.report.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReportQueryRepository {
    Optional<Report> findById(Long id);
    Page<Report> findReportsPaged(Pageable pageable);
}
