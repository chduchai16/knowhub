package com.spring.knowhub.application.queries.report;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.domain.repositories.report.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.spring.knowhub.domain.specifications.report.ReportSpecification;
import com.spring.knowhub.domain.specifications.Specification;
import org.springframework.stereotype.Component;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;

@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetPagedReportsQueryHandler implements QueryHandler<GetPagedReportsQuery, Page<Report>> {
    private final ReportRepository reportRepository;

    @Override
    public Page<Report> handle(GetPagedReportsQuery query) {
        Specification<Report> spec = new AlwaysTrueSpecification<>();

        spec = spec.and(ReportSpecification.hasKeyword(query.getKeyword()));
        spec = spec.and(ReportSpecification.hasType(query.getType()));
        spec = spec.and(ReportSpecification.hasStatus(query.getStatus()));

        return reportRepository.findReportsPaged(spec, PageRequest.of(query.getPage(), query.getLimit()));
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetPagedReportsQuery;
    }
}
