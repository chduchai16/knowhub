package com.spring.knowhub.application.exceptions.user.role;

public class UpdateRoleException extends RoleApplicationException{

    public UpdateRoleException (String message) {
        super("ROLE_UPDATE_ERROR", message);
    }

    public UpdateRoleException (String message, Throwable cause) {
        super("ROLE_UPDATE_ERROR", message, cause);
    }

    public static UpdateRoleException missingRequiredFields(String fieldName) {
        return new UpdateRoleException("Trường bắt buộc '" + fieldName + "' không được cung cấp");
    }

    public static UpdateRoleException invalidField(String fieldName, String reason) {
        return new UpdateRoleException("Trường '" + fieldName + "' không hợp lệ: " + reason);
    }

}
