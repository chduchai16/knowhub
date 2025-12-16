package com.spring.knowhub.application.exceptions.user.user;

public class CreateUserCommandException extends UserApplicationException {
    public CreateUserCommandException(String message) {
        super("CREATE_USER_COMMAND_FAILED", message);
    }

    public CreateUserCommandException(String message, Throwable cause) {
        super("CREATE_USER_COMMAND_FAILED", message, cause);
    }

    public static CreateUserCommandException missingRequiredField(String fieldName) {
        return new CreateUserCommandException("Trường bắt buộc '" + fieldName + "' không được cung cấp");
    }
}
