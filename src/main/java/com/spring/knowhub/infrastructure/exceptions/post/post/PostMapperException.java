package com.spring.knowhub.infrastructure.exceptions.post.post;

public class PostMapperException extends PostInfrastructureException{

    public PostMapperException (String message){
        super("POST_MAPPER_ERROR", message);
    }

    public PostMapperException (String message, Throwable cause){
        super("POST_MAPPER_ERROR", message, cause);
    }

    public static PostMapperException fromEntityToDomainFailed (String message){
        return new PostMapperException("Lỗi khi chuyển đổi từ PostEntity sang PostDomain: " + message);
    }

    public static PostMapperException fromDomainToEntityFailed (String message){
        return new PostMapperException("Lỗi khi chuyển đổi từ PostDomain sang PostEntity: " + message);
    }
}
