package com.spring.knowhub.domain.exceptions.media;

public class MediaDomainException extends RuntimeException{

    private String errorCode;

    public MediaDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public MediaDomainException(String errorCode, String message, Throwable cause) {
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
