package com.spring.knowhub.domain.exceptions.user.userfollow;

public class UserFollowDomainException extends RuntimeException {
    private String errorCode ;

    public UserFollowDomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public UserFollowDomainException(String errorCode, String message, Throwable cause) {
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
