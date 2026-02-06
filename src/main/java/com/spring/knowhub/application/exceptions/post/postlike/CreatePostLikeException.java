package com.spring.knowhub.application.exceptions.post.postlike;

public class CreatePostLikeException extends PostLikeApplicationException {
    public CreatePostLikeException( String message) {
        super("CREATE_POST_LIKE_ERROR" , message);
    }

    public CreatePostLikeException(String message , Throwable cause) {
        super("CREATE_POST_LIKE_ERROR" , message , cause);
    }

    public static CreatePostLikeException missingRequireField(String field) {
        return new CreatePostLikeException("Trường bắt buộc bị thiếu " + field);
    } 
}
