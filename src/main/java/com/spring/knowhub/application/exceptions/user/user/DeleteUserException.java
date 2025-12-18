package com.spring.knowhub.application.exceptions.user.user;


public class DeleteUserException extends UserApplicationException {
    public DeleteUserException(String message) {
        super("DELETE_USER_COMMAND_FAILED", message);
    }

    public DeleteUserException(String message, Throwable cause) {
        super("DELETE_USER_COMMAND_FAILED", message, cause);
    }

    public static DeleteUserException missingRequiredField(String fieldName) {
        return new DeleteUserException("Trường bắt buộc '" + fieldName + "' bị thiếu");
    }
}
