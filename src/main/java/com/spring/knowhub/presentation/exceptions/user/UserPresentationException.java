package com.spring.knowhub.presentation.exceptions.user;

public class UserPresentationException extends RuntimeException {

    private String errorCode ;

    public UserPresentationException(String errorCode , String message) {
        super(message);
        this.errorCode = errorCode ;
    }

    public UserPresentationException(String errorCode , String message, Throwable cause) {
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
