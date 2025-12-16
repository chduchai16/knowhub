package com.spring.knowhub.domain.exceptions.user.permission;


import lombok.Getter;

@Getter
public class PermissionDomainException extends RuntimeException {
    private final String errorCode;

    public PermissionDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PermissionDomainException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
