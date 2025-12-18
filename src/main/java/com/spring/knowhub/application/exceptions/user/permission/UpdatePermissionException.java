package com.spring.knowhub.application.exceptions.user.permission;

public class UpdatePermissionException extends PermissionApplicationException {

    public UpdatePermissionException(String message) {
        super("UPDATE_PERMISSION_ERROR" ,message);
    }

    public UpdatePermissionException( String message , Throwable cause) {
        super("UPDATE_PERMISSION_ERROR" ,  message , cause);
    }

    public static UpdatePermissionException missingRequiredField(String fieldName) {
        return new UpdatePermissionException("Trường bắt buộc '" + fieldName + "' bị thiếu");
    }
}
