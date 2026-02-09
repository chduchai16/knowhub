package com.spring.knowhub.domain.exceptions.comment;

public class CommentDomainException extends RuntimeException {
    private final String errorCode;

    public CommentDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CommentDomainException(String errorCode, String message, Throwable cause) {
        super(message, cause, true, false);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
