package com.spring.knowhub.domain.exceptions.post.postlike;

public class PostLikeDomainException extends RuntimeException {
    private final String errorCode;

    public PostLikeDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PostLikeDomainException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
