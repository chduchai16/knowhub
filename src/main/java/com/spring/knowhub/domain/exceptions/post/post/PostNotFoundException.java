package com.spring.knowhub.domain.exceptions.post.post;

public class PostNotFoundException extends PostDomainException{
    public PostNotFoundException(String message) {
        super("POST_NOT_FOUND", message);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super("POST_NOT_FOUND", message, cause);
    }

    public static PostNotFoundException withId(Long id) {
        return new PostNotFoundException("Bài viết với ID " + id + " không tồn tại.");
    }
}
