package com.spring.knowhub.application.validators.report;

import com.spring.knowhub.application.commands.report.CreateReportCommand;
import com.spring.knowhub.domain.enums.report.ReportType;
import com.spring.knowhub.domain.exceptions.report.InvalidReportException;

public class CreateReportValidator {
    public static void validate(CreateReportCommand command) {
        if (command == null) {
            throw new InvalidReportException("CreateReportCommand không được null");
        }

        if (command.getReporterId() == null) {
            throw new InvalidReportException("Reporter ID không được null");
        }

        if (command.getReportedEntityType() == null || command.getReportedEntityType().isBlank()) {
            throw new InvalidReportException("Loại entity không được null");
        }

        if (command.getReportedEntityId() == null) {
            throw new InvalidReportException("Entity ID không được null");
        }

        if (command.getReportType() == null) {
            throw new InvalidReportException("Loại report không được null");
        }

        if (command.getReportType() == ReportType.OTHER &&
                (command.getDescription() == null || command.getDescription().isBlank())) {
            throw InvalidReportException.missingReason();
        }

        if (command.getDescription() != null && command.getDescription().length() > 500) {
            throw new InvalidReportException("Lý do không được vượt quá 500 ký tự");
        }
    }
}
