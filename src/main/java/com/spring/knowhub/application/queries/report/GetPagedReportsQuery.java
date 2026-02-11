package com.spring.knowhub.application.queries.report;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.enums.report.ReportStatus;
import com.spring.knowhub.domain.enums.report.ReportType;
import com.spring.knowhub.domain.models.report.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class GetPagedReportsQuery implements Query<Page<Report>> {
    private int page;
    private int limit;
    private String keyword;
    private ReportType type;
    private ReportStatus status;
}