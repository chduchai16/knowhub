package com.spring.knowhub.presentation.response.report;

import com.spring.knowhub.domain.enums.report.ReportStatus;
import com.spring.knowhub.domain.enums.report.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private Long id;
    private Long reporterId;
    private String reporterUsername;
    private String reportedEntityType;
    private Long reportedEntityId;
    private ReportType reportType;
    private String description;
    private ReportStatus status;
    private LocalDateTime createdAt;
}