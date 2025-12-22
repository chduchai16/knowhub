package com.spring.knowhub.application.exceptions.user.permission;

public class GetPermissionException extends PermissionApplicationException {

    public GetPermissionException(String message) {
        super("GET_PERMISSION_ERROR",message);
    }

    public GetPermissionException (String message , Throwable cause) {
        super("GET_PERMISSION_ERROR", message , cause);
    }

    public static GetPermissionException missingRequiredField(String fieldName) {
        return new GetPermissionException("Trường bắt buộc '" + fieldName + "' bị thiếu");
    }

    public static GetPermissionException invalidField(String fieldName) {
        return new GetPermissionException("Trường '" + fieldName + "' không hợp lệ");
    }
}
