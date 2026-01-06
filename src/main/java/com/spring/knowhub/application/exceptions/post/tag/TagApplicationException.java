package com.spring.knowhub.application.exceptions.post.tag;

public class TagApplicationException extends RuntimeException{
    private String errorCode ;

    public TagApplicationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TagApplicationException (String message, String errorCode, Throwable cause) {
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
