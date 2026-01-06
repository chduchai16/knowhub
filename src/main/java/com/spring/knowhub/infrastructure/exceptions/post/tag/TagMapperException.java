package com.spring.knowhub.infrastructure.exceptions.post.tag;

public class TagMapperException extends TagInfrastructureException {

    public TagMapperException(String message) {
        super("TAG_MAPPER_ERROR", message);
    }

    public TagMapperException(String message, Throwable cause) {
        super("TAG_MAPPER_ERROR", message, cause);
    }

    public static TagMapperException fromDomainToEntityMappingError(String details) {
        return new TagMapperException("Lỗi ánh xạ từ Domain đến Entity cho Tag. Chi tiết: " + details);
    }

    public static TagMapperException fromEntityToDomainMappingError(String details) {
        return new TagMapperException("Lỗi ánh xạ từ Entity đến Domain cho Tag. Chi tiết: " + details);
    }
}
