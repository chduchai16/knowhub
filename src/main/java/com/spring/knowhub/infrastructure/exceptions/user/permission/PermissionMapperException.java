package com.spring.knowhub.infrastructure.exceptions.user.permission;

public class PermissionMapperException extends PermissionInfrastructureException {

    public PermissionMapperException(String message) {
        super("PERMISSION_MAPPER_ERROR" ,message);
    }

    public PermissionMapperException(String message, Throwable cause) {
        super("PERMISSION_MAPPER_ERROR" ,message, cause);
    }

    public static PermissionMapperException entityToDomainMappingFailed(String details) {
        return new PermissionMapperException("Lỗi khi map Permission Entity sang Permission Domain: " + details);
    }

    public static PermissionMapperException domainToEntityMappingFailed(String details) {
        return new PermissionMapperException("Lỗi khi map Permission Domain sang Permission Entity: " + details);
    }

}
