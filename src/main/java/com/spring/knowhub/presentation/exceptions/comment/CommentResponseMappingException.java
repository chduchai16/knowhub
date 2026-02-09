package com.spring.knowhub.presentation.exceptions.comment;

public class CommentResponseMappingException extends CommentPresentationException {

    private CommentResponseMappingException(String message) {
        super("COMMENT_MAPPING_ERROR", message);
    }

    private CommentResponseMappingException(String message, Throwable cause) {
        super("COMMENT_MAPPING_ERROR", message, cause);
    }

    public static CommentResponseMappingException objectNull() {
        return new CommentResponseMappingException("Đối tượng Comment truyền vào là null");
    }

    public static CommentResponseMappingException errorMapping(Exception exception) {
        return new CommentResponseMappingException(
                "Lỗi khi mapping Comment sang CommentResponse: " + exception.getMessage());
    }
}
