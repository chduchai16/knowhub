package com.spring.knowhub.presentation.requests.report;

import com.spring.knowhub.domain.enums.report.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReportRequest {
    private Long id;
    private ReportStatus status;
    private String description;
}
