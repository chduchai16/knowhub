package com.spring.knowhub.infrastructure.exceptions.post.postmedia;

public class PostMediaRepositoryException extends PostMediaInfrastructureException{

    public PostMediaRepositoryException (String message){
        super("POST_MEDIA_REPOSITORY_EXCEPTION",message);
    }

    public PostMediaRepositoryException (String message, Throwable cause){
        super("POST_MEDIA_REPOSITORY_EXCEPTION",message,cause);
    }

    public static PostMediaRepositoryException saveFailed(String details) {
        return new PostMediaRepositoryException("Lỗi khi lưu PostMedia vào kho dữ liệu. Chi tiết: " + details);
    }

    public static PostMediaRepositoryException deleteFailed(String details) {
        return new PostMediaRepositoryException("Lỗi khi xóa PostMedia khỏi kho dữ liệu. Chi tiết: " + details);
    }

    public static PostMediaRepositoryException findFailed(String details) {
        return new PostMediaRepositoryException("Không tìm thấy PostMedia trong kho dữ liệu. Chi tiết: " + details);
    }
}
