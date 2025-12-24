package com.spring.knowhub.application.exceptions.auth;

public class AuthApplicationException extends RuntimeException {

    private String errorCode ;

    public AuthApplicationException( String errorCode , String message) {
        super(message);
        this.errorCode = errorCode ;
    }

    public AuthApplicationException (String errorCode , String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode ;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
