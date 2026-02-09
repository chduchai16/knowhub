package com.spring.knowhub.domain.exceptions.comment;

public class CommentNotFoundException extends CommentDomainException {
    public CommentNotFoundException(String message) {
        super("COMMENT_NOT_FOUND", message);
    }

    public static CommentNotFoundException withId(Long id) {
        return new CommentNotFoundException("Bình luận với ID " + id + " không tồn tại.");
    }
}
