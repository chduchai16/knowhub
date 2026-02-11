package com.spring.knowhub.application.commands.report;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.exceptions.report.ReportNotFoundException;
import com.spring.knowhub.domain.models.report.Report;
import com.spring.knowhub.domain.repositories.report.ReportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class UpdateReportCommandHandler implements CommandHandler<UpdateReportCommand, Long> {
    private final ReportRepository reportRepository;

    @Override
    public Long handle(UpdateReportCommand command) {
        Report report = reportRepository.findById(command.getId())
                .orElseThrow(() -> ReportNotFoundException.byId(command.getId()));

        if (command.getStatus() != null) {
            report.setStatus(command.getStatus());
        }
        if (command.getDescription() != null) {
            report.setDescription(command.getDescription());
        }

        return reportRepository.save(report).getId();
    }

    @Override
    public boolean supports(Object command) {
        return command instanceof UpdateReportCommand;
    }
}
