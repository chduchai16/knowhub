package com.spring.knowhub.application.exceptions.user.role;

public class CreateRoleException extends RoleApplicationException{

    public CreateRoleException (String message) {
        super("ROLE_CREATE_ERROR", message);
    }

    public CreateRoleException ( String message, Throwable cause) {
        super("ROLE_CREATE_ERROR", message, cause);
    }

    public static CreateRoleException missingRequiredField(String fieldName) {
        return new CreateRoleException("Trường bắt buộc '" + fieldName + "' không được cung cấp");
    }

    public static CreateRoleException invalidField(String fieldName, String reason) {
        return new CreateRoleException("Trường '" + fieldName + "' không hợp lệ: " + reason);
    }
}
