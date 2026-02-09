package com.spring.knowhub.infrastructure.exceptions.notification;

public class NotificationInfrastructureException extends RuntimeException {
    private final String errorCode;

    public NotificationInfrastructureException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public NotificationInfrastructureException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
