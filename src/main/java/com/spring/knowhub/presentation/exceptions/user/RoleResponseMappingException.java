package com.spring.knowhub.presentation.exceptions.user;

public class RoleResponseMappingException extends RolePresentationException{
    public RoleResponseMappingException( String message) {
        super("ROLE_RESPONSE_MAPPING_ERROR", message);
    }

    public RoleResponseMappingException(String message, Throwable cause) {
        super("ROLE_RESPONSE_MAPPING_ERROR", message, cause);
    }

    public static RoleResponseMappingException objectNull() {
        return new RoleResponseMappingException("Không thể ánh xạ RoleResponse do đối tượng nguồn là null");
    }

    public static RoleResponseMappingException mappingError(Exception cause) {
        return new RoleResponseMappingException("Đã xảy ra lỗi khi ánh xạ RoleResponse: " + cause.getMessage(), cause);
    }

}
