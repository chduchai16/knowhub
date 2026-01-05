package com.spring.knowhub.infrastructure.exceptions.media;

public class MediaMapperException extends MediaInfrastructureException{

    public MediaMapperException(String message) {
        super("MEDIA_MAPPER_ERROR", message);
    }

    public MediaMapperException(String message, Throwable cause) {
        super("MEDIA_MAPPER_ERROR", message, cause);
    }

    public static MediaMapperException fromEntityToDomainFailed(String message) {
        return new MediaMapperException("Lỗi khi chuyển đổi từ MediaEntity sang MediaDomain: " + message);
    }

    public static MediaMapperException fromDomainToEntityFailed(String message) {
        return new MediaMapperException("Lỗi khi chuyển đổi từ MediaDomain sang MediaEntity: "+ message);
    }
}
