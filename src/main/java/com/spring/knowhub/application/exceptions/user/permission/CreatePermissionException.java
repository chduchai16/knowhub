package com.spring.knowhub.application.exceptions.user.permission;

public class CreatePermissionException extends PermissionApplicationException {
    public CreatePermissionException(String message) {
        super("CREATE_PERMISSION_ERROR",message);
    }

    public CreatePermissionException (String message , Throwable cause) {
        super("CREATE_PERMISSION_ERROR", message , cause);
    }

    public static CreatePermissionException missingRequiredField(String fieldName) {
        return new CreatePermissionException("Trường bắt buộc '" + fieldName + "' bị thiếu");
    }
}
