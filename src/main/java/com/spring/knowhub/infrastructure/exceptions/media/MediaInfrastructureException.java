package com.spring.knowhub.infrastructure.exceptions.media;

public class MediaInfrastructureException extends RuntimeException{
    private String errorCode;

    public MediaInfrastructureException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public MediaInfrastructureException(String errorCode, String message, Throwable cause) {
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
