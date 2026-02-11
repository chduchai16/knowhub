package com.spring.knowhub.application.commands.report;

import com.spring.knowhub.application.buses.Command;
import com.spring.knowhub.domain.enums.report.ReportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReportCommand implements Command<Long> {
    private Long reporterId;
    private String reportedEntityType;
    private Long reportedEntityId;
    private ReportType reportType;
    private String description;
}
