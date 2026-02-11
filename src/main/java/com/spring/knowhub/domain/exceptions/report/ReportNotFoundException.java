package com.spring.knowhub.domain.exceptions.report;

public class ReportNotFoundException extends ReportDomainException {
    public ReportNotFoundException(String message) {
        super("REPORT_NOT_FOUND", message);
    }

    public ReportNotFoundException(String message, Throwable cause) {
        super("REPORT_NOT_FOUND", message, cause);
    }

    public static ReportNotFoundException byId(Long reportId) {
        return new ReportNotFoundException("Report với ID '" + reportId + "' không tồn tại");
    }
}
