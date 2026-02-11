package com.spring.knowhub.application.queries.report;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.domain.repositories.report.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetReportsByUserIdQueryHandler implements QueryHandler<GetReportsByUserIdQuery, Page<Report>> {
    private final ReportRepository reportRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetReportsByUserIdQuery;
    }

    @Override
    public Page<Report> handle(GetReportsByUserIdQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        return reportRepository.findByReporterId(query.getUserId(), pageable);
    }
}
