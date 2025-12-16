package com.spring.knowhub.application.exceptions.user.user;


public class UserApplicationException extends RuntimeException {
    private String errorCode;
    private String message;

    public UserApplicationException(String message) {
        super(message);
        this.message = message;
    }

    public UserApplicationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public UserApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public UserApplicationException(String errorCode, String message, Throwable cause) {
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
