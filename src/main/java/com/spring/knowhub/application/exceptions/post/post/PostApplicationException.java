package com.spring.knowhub.application.exceptions.post.post;

public class PostApplicationException extends RuntimeException{

    private String errorCode ;

    public PostApplicationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public PostApplicationException(String message, String errorCode, Throwable cause) {
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
