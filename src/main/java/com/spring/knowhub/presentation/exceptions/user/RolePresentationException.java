package com.spring.knowhub.presentation.exceptions.user;

public class RolePresentationException extends RuntimeException {

    private String errorCode;

    public RolePresentationException( String errorCode , String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RolePresentationException ( String errorCode , String message, Throwable cause) {
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
