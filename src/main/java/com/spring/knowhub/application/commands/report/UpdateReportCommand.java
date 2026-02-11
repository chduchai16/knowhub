package com.spring.knowhub.application.commands.report;

import com.spring.knowhub.application.buses.Command;
import com.spring.knowhub.domain.enums.report.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateReportCommand implements Command<Long> {
    private Long id;
    private ReportStatus status;
    private String description;
}