package com.spring.knowhub.infrastructure.exceptions.post.post;

public class PostInfrastructureException extends RuntimeException{
    private String errorCode ;

    public PostInfrastructureException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PostInfrastructureException(String errorCode , String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode(){
        return errorCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
