package com.spring.knowhub.application.exceptions.user.user;

public class UpdateUserCommandException extends UserApplicationException {
    public UpdateUserCommandException(String message) {
        super("UPDATE_USER_COMMAND_FAILED", message);
    }

    public UpdateUserCommandException(String message, Throwable cause) {
        super("UPDATE_USER_COMMAND_FAILED", message, cause);
    }

    public static UpdateUserCommandException missingRequiredField(String fieldName) {
        return new UpdateUserCommandException("Trường bắt buộc '" + fieldName + "' bị thiếu");
    }

}
