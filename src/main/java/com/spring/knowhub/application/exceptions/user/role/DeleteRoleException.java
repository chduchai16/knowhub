package com.spring.knowhub.application.exceptions.user.role;

public class DeleteRoleException extends RoleApplicationException{

    public DeleteRoleException(String message) {
        super("DELETE_ROLE_ERROR",message);
    }

    public DeleteRoleException(String message, Throwable cause) {
        super("DELETE_ROLE_ERROR",message,cause);
    }

    public static DeleteRoleException invalidField(String fieldName, String reason) {
        return new DeleteRoleException("Trường '" + fieldName + "' không hợp lệ: " + reason);
    }

}
