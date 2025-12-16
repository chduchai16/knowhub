package com.spring.knowhub.domain.exceptions.user.permission;

public class DuplicatePermissionException extends PermissionDomainException {
    public DuplicatePermissionException(String message) {
        super("DUPLICATE_PERMISSION" , message);
    }

    public DuplicatePermissionException(String message, Throwable cause) {
        super("DUPLICATE_PERMISSION", message, cause);
    }

    public static DuplicatePermissionException DuplicattePermissionException (String code){
        return new DuplicatePermissionException("Quyền với mã '" + code + "' đã tồn tại");
    }
}
