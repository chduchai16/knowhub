package com.spring.knowhub.domain.exceptions.report;

public class ReportDomainException extends RuntimeException {

    private final String errorCode;

    public ReportDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ReportDomainException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
