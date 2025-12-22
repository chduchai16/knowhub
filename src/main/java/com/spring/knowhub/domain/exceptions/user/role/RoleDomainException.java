package com.spring.knowhub.domain.exceptions.user.role;

public class RoleDomainException extends RuntimeException{
    private final String errorCode;

    public RoleDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RoleDomainException(String errorCode, String message, Throwable cause) {
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
