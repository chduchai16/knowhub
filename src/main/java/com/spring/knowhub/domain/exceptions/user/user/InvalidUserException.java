package com.spring.knowhub.domain.exceptions.user.user;

// exception khi user không hợp lệ
public class InvalidUserException extends UserDomainException {
    public InvalidUserException(String message) {
        super("INVALID_USER", message);
    }

    public InvalidUserException(String message, Throwable cause) {
        super("INVALID_USER", message, cause);
    }

    public static InvalidUserException invalidEmail(String email) {
        return new InvalidUserException("Email '" + email + "' không hợp lệ");
    }

    public static InvalidUserException invalidUsername(String username) {
        return new InvalidUserException("Username '" + username + "' không tuân theo định dạng");
    }

    public static InvalidUserException weakPassword() {
        return new InvalidUserException("Password phải có ít nhất 8 ký tự, chứa chữ hoa, chữ thường và số");
    }

    public static InvalidUserException emptyFullName() {
        return new InvalidUserException("Fullname không được để trống");
    }
}
