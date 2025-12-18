package com.spring.knowhub.domain.exceptions.user.permission;

public class InvalidPermissionException extends PermissionDomainException {

    public InvalidPermissionException(String message) {
        super("INVALID_PERMISSION", message);
    }

    public InvalidPermissionException(String message, Throwable cause) {
        super("INVALID_PERMISSION", message, cause);
    }

    public static InvalidPermissionException InvalidPermissionCodeException(String code) {
        return new InvalidPermissionException("Quyền với mã '" + code + "' không hợp lệ");
    }

}
