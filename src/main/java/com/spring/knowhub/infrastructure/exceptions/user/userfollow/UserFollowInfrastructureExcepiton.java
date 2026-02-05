package com.spring.knowhub.infrastructure.exceptions.user.userfollow;

public class UserFollowInfrastructureExcepiton extends RuntimeException{
    private String errorCode ;

    public UserFollowInfrastructureExcepiton(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public UserFollowInfrastructureExcepiton(String errorCode, String message, Throwable cause) {
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
