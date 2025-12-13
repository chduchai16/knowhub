package com.spring.knowhub.infrastructure.repositories.jpas.report;

import com.spring.knowhub.infrastructure.entities.report.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaReportRepository extends JpaRepository<ReportEntity , Long> {
}
