package com.spring.knowhub.presentation.exceptions.post;

public class PostResponseMappingException extends PostPresentationException {

    private PostResponseMappingException(String message) {
        super("POST_MAPPING_ERROR" , message);
    }

    private PostResponseMappingException(String message, Throwable cause) {
        super("POST_MAPPING_ERROR" ,message, cause);
    }

    public static PostResponseMappingException objectNull() {
        return new PostResponseMappingException("Đối tượng Post truyền vào là null");
    }

    public static PostResponseMappingException errorMapping(Exception exception) {
        return new PostResponseMappingException("Lỗi khi mapping Post sang PostResponse: " + exception.getMessage());
    }
}
