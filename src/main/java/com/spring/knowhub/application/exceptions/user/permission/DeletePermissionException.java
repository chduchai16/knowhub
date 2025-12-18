package com.spring.knowhub.application.exceptions.user.permission;

public class DeletePermissionException extends PermissionApplicationException {

    public DeletePermissionException(String message) {
        super("DELETE_PERMISSION_ERROR" ,message);
    }

    public DeletePermissionException( String message , Throwable cause) {
        super("DELETE_PERMISSION_ERROR" ,  message , cause);
    }

    public static DeletePermissionException missingRequiredField(String fieldName) {
        return new DeletePermissionException("Trường bắt buộc '" + fieldName + "' bị thiếu");
    }
}
