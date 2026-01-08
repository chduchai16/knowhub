package com.spring.knowhub.infrastructure.exceptions.post.postmedia;

public class PostMediaMapperException extends PostMediaInfrastructureException{
    public PostMediaMapperException (String message){
        super("POST_MEDIA_MAPPER_EXCEPTION",message);
    }

    public PostMediaMapperException (String message, Throwable cause){
        super("POST_MEDIA_MAPPER_EXCEPTION",message,cause);
    }

    public static PostMediaMapperException fromDomainToEntityMappingError(String details) {
        return new PostMediaMapperException("Lỗi ánh xạ từ Domain đến Entity cho PostMedia. Chi tiết: " + details);
    }

    public static PostMediaMapperException fromEntityToDomainMappingError(String details) {
        return new PostMediaMapperException("Lỗi ánh xạ từ Entity đến Domain cho PostMedia. Chi tiết: " + details);
    }

}
