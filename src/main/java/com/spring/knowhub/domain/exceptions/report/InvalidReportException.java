package com.spring.knowhub.domain.exceptions.report;

public class InvalidReportException extends ReportDomainException {
    public InvalidReportException(String message) {
        super("INVALID_REPORT", message);
    }

    public InvalidReportException(String message, Throwable cause) {
        super("INVALID_REPORT", message, cause);
    }

    public static InvalidReportException cannotReportOwnContent() {
        return new InvalidReportException("Không thể report nội dung của chính mình");
    }

    public static InvalidReportException missingReason() {
        return new InvalidReportException("Vui lòng cung cấp lý do khi chọn loại report OTHER");
    }
}
