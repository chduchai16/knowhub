package com.spring.knowhub.domain.exceptions.post.post;

public class InvalidPostException extends PostDomainException{

    public InvalidPostException (String message) {
        super("INVALID_POST_ERROR" , message);
    }

    public InvalidPostException (String message , Throwable cause) {
        super("INVALID_POST_ERROR" , message , cause);
    }

    public static InvalidPostException mediaLimitExceeded(int limit) {
        return new InvalidPostException("Media cho bài viết vượt quá giới hạn cho phép: " + limit);
    }

    public static InvalidPostException tagLimitExceeded(int limit) {
        return new InvalidPostException("Số lượng tag trong bài viết vượt quá giới hạn cho phép: " + limit);
    }

    public static InvalidPostException contentTooLong(int maxLength) {
        return new InvalidPostException("Nội dung bài viết vượt quá độ dài tối đa cho phép: " + maxLength + " ký tự.");
    }

    public static InvalidPostException privacyInvalid () {
        return new InvalidPostException("Cài đặt quyền truy cập của bài viết không hợp lệ.");
    }

    public static InvalidPostException emptyContent() {
        return new InvalidPostException("Nội dung bài viết không được để trống.");
    }

}
