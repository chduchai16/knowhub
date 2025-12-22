package com.spring.knowhub.infrastructure.exceptions.user.user;

// exception khi map user giữa entity và domain
public class UserMapperException extends UserInfrastructureException {
    public UserMapperException(String message) {
        super("USER_MAPPER_ERROR", message);
    }

    public UserMapperException(String message, Throwable cause) {
        super("USER_MAPPER_ERROR", message, cause);
    }

    public static UserMapperException entityToDomainMappingFailed(String details) {
        return new UserMapperException("Lỗi khi map User Entity sang User domain: " + details);
    }

    public static UserMapperException domainToEntityMappingFailed(String details) {
        return new UserMapperException("Lỗi khi map User domain sang User Entity: " + details);
    }
}
