package com.spring.knowhub.domain.exceptions.user.user;

public class UserNotFoundException extends UserDomainException {
    public UserNotFoundException(String message) {
        super("USER_NOT_FOUND", message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super("USER_NOT_FOUND", message, cause);
    }

    public static UserNotFoundException byId(Long userId) {
        return new UserNotFoundException("User với ID '" + userId + "' không tồn tại");
    }

    public static UserNotFoundException byUsername(String username) {
        return new UserNotFoundException("User với username '" + username + "' không tồn tại");
    }

}