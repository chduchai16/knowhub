package com.spring.knowhub.infrastructure.exceptions.user.role;

public class RoleInfrastructureException extends RuntimeException {

    private String errorCode;

    public RoleInfrastructureException(String message) {
        super(message);
    }

    public RoleInfrastructureException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RoleInfrastructureException(String errorCode, String message, Throwable cause) {
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
