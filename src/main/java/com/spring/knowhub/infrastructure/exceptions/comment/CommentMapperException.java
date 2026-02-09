package com.spring.knowhub.infrastructure.exceptions.comment;

public class CommentMapperException extends CommentInfrastructureException {
    public CommentMapperException(String message) {
        super("COMMENT_MAPPER_ERROR", message);
    }

    public CommentMapperException(String message, Throwable cause) {
        super("COMMENT_MAPPER_ERROR", message, cause);
    }
}
