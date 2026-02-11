package com.spring.knowhub.presentation.requests.report;

import com.spring.knowhub.domain.enums.report.ReportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReportRequest {
    private String reportedEntityType;
    private Long reportedEntityId;
    private ReportType reportType;
    private String description;
}
