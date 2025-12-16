package com.spring.knowhub.infrastructure.exceptions.user;

public class UserInfrastructureException extends RuntimeException {
    private String errorCode;
    private String message;

    public UserInfrastructureException(String message) {
        super(message);
        this.message = message;
    }

    public UserInfrastructureException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public UserInfrastructureException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public UserInfrastructureException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
