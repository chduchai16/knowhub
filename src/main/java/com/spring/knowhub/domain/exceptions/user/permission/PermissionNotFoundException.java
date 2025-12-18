package com.spring.knowhub.domain.exceptions.user.permission;

public class PermissionNotFoundException extends PermissionDomainException {
    public PermissionNotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }

    public PermissionNotFoundException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public static PermissionNotFoundException permissionNotFoundByCode(String code) {
        return new PermissionNotFoundException("PERMISSION_NOT_FOUND", "Không tìm thấy quyền với mã '" + code + "'");
    }

    public static PermissionNotFoundException permissionNotFoundById(Long id) {
        return new PermissionNotFoundException("PERMISSION_NOT_FOUND", "Không tìm thấy quyền với ID '" + id + "'");
    }
}
