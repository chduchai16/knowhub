package com.spring.knowhub.domain.exceptions.post.post;

public class PostDomainException extends RuntimeException{
    private String errorCode ;

    public PostDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PostDomainException(String errorCode , String message, Throwable cause) {
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
