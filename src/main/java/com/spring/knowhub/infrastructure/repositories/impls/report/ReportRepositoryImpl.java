package com.spring.knowhub.infrastructure.repositories.impls.report;

import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.domain.repositories.report.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class ReportRepositoryImpl implements ReportRepository {
    @Override
    public Optional<Report> save(Report report) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Report> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Report> findReportsPaged(Pageable pageable) {
        return null;
    }
}
