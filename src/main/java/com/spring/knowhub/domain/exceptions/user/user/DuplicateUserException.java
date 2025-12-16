package com.spring.knowhub.domain.exceptions.user.user;

// exception khi user bị trùng
public class DuplicateUserException extends UserDomainException {
    public DuplicateUserException(String message) {
        super("DUPLICATE_USER", message);
    }

    public DuplicateUserException(String message, Throwable cause) {
        super("DUPLICATE_USER", message, cause);
    }

    public static DuplicateUserException emailAlreadyExists(String email) {
        return new DuplicateUserException("Email '" + email + "' đã được sử dụng");
    }

    public static DuplicateUserException usernameAlreadyExists(String username) {
        return new DuplicateUserException("Username '" + username + "' đã được sử dụng");
    }
}
