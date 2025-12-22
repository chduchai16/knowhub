package com.spring.knowhub.domain.exceptions.user.role;

public class RoleNotFoundException extends RoleDomainException{

    public RoleNotFoundException(String message) {
        super("ROLE_NOT_FOUND", message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super("ROLE_NOT_FOUND", message, cause);
    }

    public static RoleNotFoundException byId(Long roleId) {
        return new RoleNotFoundException("Role với ID '" + roleId + "' không tồn tại");
    }

    public static RoleNotFoundException byName(String roleName) {
        return new RoleNotFoundException("Role với tên '" + roleName + "' không tồn tại");
    }
}
