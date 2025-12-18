package com.spring.knowhub.application.exceptions.user.user;

public class UpdateUserException extends UserApplicationException {
    public UpdateUserException(String message) {
        super("UPDATE_USER_COMMAND_FAILED", message);
    }

    public UpdateUserException(String message, Throwable cause) {
        super("UPDATE_USER_COMMAND_FAILED", message, cause);
    }

    public static UpdateUserException missingRequiredField(String fieldName) {
        return new UpdateUserException("Trường bắt buộc '" + fieldName + "' bị thiếu");
    }

}
