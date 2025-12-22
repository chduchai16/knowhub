package com.spring.knowhub.infrastructure.exceptions.user.role;

public class RoleMapperException extends RoleInfrastructureException{

    public RoleMapperException( String message) {
        super("ROLE_MAPPER_ERROR", message);
    }

    public RoleMapperException(String message, Throwable cause) {
        super("ROLE_MAPPER_ERROR",message, cause);
    }

    public static RoleMapperException entityToDomainMappingFailed(String details) {
        return new RoleMapperException("Lỗi khi map Role Entity sang Role Domain: " + details);
    }

    public static RoleMapperException domainToEntityMappingFailed(String details) {
        return new RoleMapperException("Lỗi khi map Role Domain sang Role Entity: " + details);
    }
}
