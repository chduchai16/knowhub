package com.spring.knowhub.domain.exceptions.user.permission;


public class PermissionDomainException extends RuntimeException {
    private final String errorCode;

    public PermissionDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PermissionDomainException(String errorCode, String message, Throwable cause) {
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
