package com.spring.knowhub.domain.exceptions.message;

public class MessageDomainException extends RuntimeException {
    private final String errorCode;

    public MessageDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
