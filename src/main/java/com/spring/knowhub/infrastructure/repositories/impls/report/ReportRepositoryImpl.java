package com.spring.knowhub.infrastructure.repositories.impls.report;

import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.domain.repositories.report.ReportRepository;
import com.spring.knowhub.infrastructure.entities.report.ReportEntity;
import com.spring.knowhub.infrastructure.mappers.report.ReportMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.report.JpaReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {
    private final JpaReportRepository jpaReportRepository;
    private final ReportMapper reportMapper;

    @Override
    public Report save(Report report) {
        ReportEntity entity = reportMapper.fromDomainToEntity(report);
        ReportEntity savedEntity = jpaReportRepository.save(entity);
        return reportMapper.fromEntityToDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaReportRepository.deleteById(id);
    }

    @Override
    public Optional<Report> findById(Long id) {
        return jpaReportRepository.findById(id).map(reportMapper::fromEntityToDomain);
    }

    @Override
    public Page<Report> findReportsPaged(Pageable pageable) {
        return jpaReportRepository.findAll(pageable).map(reportMapper::fromEntityToDomain);
    }

    @Override
    public Page<Report> findByReporterId(Long reporterId, Pageable pageable) {
        return jpaReportRepository.findByReporterId(reporterId, pageable).map(reportMapper::fromEntityToDomain);
    }

    @Override
    public boolean existsByReporterIdAndPostId(Long reporterId, Long postId) {
        return jpaReportRepository.existsByReporterIdAndPostId(reporterId, postId);
    }

    @Override
    public boolean existsByReporterIdAndCommentId(Long reporterId, Long commentId) {
        return jpaReportRepository.existsByReporterIdAndCommentId(reporterId, commentId);
    }
}
