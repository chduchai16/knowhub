package com.spring.knowhub.domain.exceptions.notification;

public class NotificationDomainException extends RuntimeException {
    private final String errorCode;

    public NotificationDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public NotificationDomainException(String errorCode, String message, Throwable cause) {
        super(message, cause, true, false);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
