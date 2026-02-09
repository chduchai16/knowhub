package com.spring.knowhub.presentation.exceptions.comment;

public class CommentPresentationException extends RuntimeException {
    private final String errorCode;

    public CommentPresentationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CommentPresentationException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
