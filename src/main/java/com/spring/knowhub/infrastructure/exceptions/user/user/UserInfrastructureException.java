package com.spring.knowhub.infrastructure.exceptions.user.user;

public class UserInfrastructureException extends RuntimeException {
    private String errorCode;

    public UserInfrastructureException(String message) {
        super(message);
    }

    public UserInfrastructureException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public UserInfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInfrastructureException(String errorCode, String message, Throwable cause) {
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
