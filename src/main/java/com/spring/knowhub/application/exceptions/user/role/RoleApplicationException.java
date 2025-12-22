package com.spring.knowhub.application.exceptions.user.role;

public class RoleApplicationException extends RuntimeException {
    private String errorCode;

    public RoleApplicationException(String message) {
        super(message);
    }

    public RoleApplicationException (String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RoleApplicationException (String errorCode , String message, Throwable cause) {
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
