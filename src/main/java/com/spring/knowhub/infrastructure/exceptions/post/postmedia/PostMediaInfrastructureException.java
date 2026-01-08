package com.spring.knowhub.infrastructure.exceptions.post.postmedia;

public class PostMediaInfrastructureException extends RuntimeException{
    private String errorCode ;

    public PostMediaInfrastructureException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public PostMediaInfrastructureException(String message, String errorCode, Throwable cause) {
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
