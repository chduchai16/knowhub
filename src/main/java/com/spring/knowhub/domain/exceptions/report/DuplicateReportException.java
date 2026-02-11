package com.spring.knowhub.domain.exceptions.report;

public class DuplicateReportException extends ReportDomainException {
    public DuplicateReportException(String message) {
        super("DUPLICATE_REPORT", message);
    }

    public DuplicateReportException(String message, Throwable cause) {
        super("DUPLICATE_REPORT", message, cause);
    }

    public static DuplicateReportException forEntity(String entityType, Long entityId) {
        return new DuplicateReportException("Bạn đã report " + entityType + " với ID '" + entityId + "' rồi");
    }
}
