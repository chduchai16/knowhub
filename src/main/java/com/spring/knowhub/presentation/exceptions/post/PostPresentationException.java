package com.spring.knowhub.presentation.exceptions.post;

public class PostPresentationException extends RuntimeException{
    private String errorCode ;

    public PostPresentationException(String errorCode , String message) {
        super(message);
        this.errorCode = errorCode ;
    }

    public PostPresentationException(String errorCode , String message, Throwable cause) {
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
