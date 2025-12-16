package com.spring.knowhub.application.exceptions.user.user;


public class DeleteUserCommandException extends UserApplicationException {
    public DeleteUserCommandException(String message) {
        super("DELETE_USER_COMMAND_FAILED", message);
    }

    public DeleteUserCommandException(String message, Throwable cause) {
        super("DELETE_USER_COMMAND_FAILED", message, cause);
    }

    public static DeleteUserCommandException missingRequiredField(String fieldName) {
        return new DeleteUserCommandException("Trường bắt buộc '" + fieldName + "' bị thiếu");
    }
}
