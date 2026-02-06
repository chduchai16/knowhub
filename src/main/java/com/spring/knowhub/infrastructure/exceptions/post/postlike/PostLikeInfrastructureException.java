package com.spring.knowhub.infrastructure.exceptions.post.postlike;

public class PostLikeInfrastructureException extends RuntimeException {
    private String errorCode ; 

    public PostLikeInfrastructureException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public PostLikeInfrastructureException(String message, String errorCode, Throwable cause) {
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
