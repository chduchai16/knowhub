package com.spring.knowhub.infrastructure.exceptions.comment;

public class CommentInfrastructureException extends RuntimeException {
    private final String errorCode;

    public CommentInfrastructureException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CommentInfrastructureException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
