package com.spring.knowhub.infrastructure.exceptions.post.postlike;

public class PostLikeMappingException extends PostLikeInfrastructureException {

    public PostLikeMappingException(String message) {
        super("POST_LIKE_MAPPING_EXCEPTION", message);
    }

    public PostLikeMappingException(String message, Throwable cause) {
        super("POST_LIKE_MAPPING_EXCEPTION", message, cause);
    }
    
    public static PostLikeMappingException fromEntityToDomainFailed(String message) {
        return new PostLikeMappingException("Lỗi mapping PostLike từ entity sang domain: " + message);
    }

    public static PostLikeMappingException fromDomainToEntityFailed(String message) {
        return new PostLikeMappingException("Lỗi mapping PostLike từ domain sang entity: " + message);
    }
}
