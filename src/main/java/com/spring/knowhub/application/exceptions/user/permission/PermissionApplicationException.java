package com.spring.knowhub.application.exceptions.user.permission;

public class PermissionApplicationException extends RuntimeException {

    private String errorCode;

    public PermissionApplicationException(String message) {
        super(message);
    }

    public PermissionApplicationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PermissionApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionApplicationException(String errorCode, String message, Throwable cause) {
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
