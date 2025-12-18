package com.spring.knowhub.application.exceptions.user.user;

public class CreateUserException extends UserApplicationException {
    public CreateUserException(String message) {
        super("CREATE_USER_COMMAND_FAILED", message);
    }

    public CreateUserException(String message, Throwable cause) {
        super("CREATE_USER_COMMAND_FAILED", message, cause);
    }

    public static CreateUserException missingRequiredField(String fieldName) {
        return new CreateUserException("Trường bắt buộc '" + fieldName + "' không được cung cấp");
    }
}
