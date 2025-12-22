package com.spring.knowhub.domain.exceptions.user.role;

public class DuplicateRoleException extends  RoleDomainException{
    public DuplicateRoleException(String message) {
        super("DUPLICATE_ROLE", message);
    }

    public DuplicateRoleException(String message, Throwable cause) {
        super("DUPLICATE_ROLE", message, cause);
    }

    public static DuplicateRoleException byName(String roleName) {
        return new DuplicateRoleException("Role với tên '" + roleName + "' đã tồn tại");
    }
}
