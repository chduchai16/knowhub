package com.spring.knowhub.infrastructure.exceptions.user.permission;

public class PermissionInfrastructureException extends RuntimeException {
    private String errorCode;
    public PermissionInfrastructureException(String message) {
        super(message);
    }

    public PermissionInfrastructureException (String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PermissionInfrastructureException (String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionInfrastructureException (String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
