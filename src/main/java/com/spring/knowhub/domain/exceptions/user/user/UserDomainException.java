package com.spring.knowhub.domain.exceptions.user.user;

import lombok.Getter;


@Getter
public class UserDomainException extends RuntimeException {

    private final String errorCode;


    public UserDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public UserDomainException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
