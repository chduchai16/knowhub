package com.spring.knowhub.application.exceptions.user.role;

public class GetRoleException extends RoleApplicationException{

    public GetRoleException (String message) {
        super("ROLE_GET_ERROR", message);
    }

    public GetRoleException ( String message, Throwable cause) {
        super("ROLE_GET_ERROR", message, cause);
    }

    public static GetRoleException roleNotFound(Long roleId) {
        return new GetRoleException("Không tìm thấy vai trò với ID: " + roleId);
    }

    public static GetRoleException invalidField(String fieldName) {
        return new GetRoleException("Trường không hợp lệ: " + fieldName);
    }
}
