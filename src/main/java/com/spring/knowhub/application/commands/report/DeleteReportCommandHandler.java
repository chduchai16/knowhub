package com.spring.knowhub.application.commands.report;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.repositories.report.ReportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class DeleteReportCommandHandler implements CommandHandler<DeleteReportCommand, Void> {
    private final ReportRepository reportRepository;

    @Override
    public Void handle(DeleteReportCommand command) {
        reportRepository.deleteById(command.getId());
        return null;
    }

    @Override
    public boolean supports(Object command) {
        return command instanceof DeleteReportCommand;
    }
}
