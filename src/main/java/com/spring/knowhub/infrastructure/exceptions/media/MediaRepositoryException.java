package com.spring.knowhub.infrastructure.exceptions.media;

import com.spring.knowhub.domain.exceptions.media.InvalidMediaException;

public class MediaRepositoryException extends MediaInfrastructureException {

    public MediaRepositoryException(String message) {
        super("MEDIA_REPOSITORY_ERROR", message);
    }

    public MediaRepositoryException(String message, Throwable cause) {
        super("MEDIA_REPOSITORY_ERROR", message, cause);
    }

    public static InvalidMediaException saveFailed(String message) {
        return new InvalidMediaException("Lỗi khi thực hiện lưu media: " + message);
    }

    public static InvalidMediaException deleteFailed(String message) {
        return new InvalidMediaException("Lỗi khi thực hiện xóa media: " + message);
    }

    public static InvalidMediaException findFailed(String message) {
        return new InvalidMediaException("Lỗi khi thực hiện tìm kiếm media: " + message);
    }

    public static InvalidMediaException updateFailed(String message) {
        return new InvalidMediaException("Lỗi khi thực hiện cập nhật media: " + message);
    }

    public static InvalidMediaException saveAllFailed(String message) {
        return new InvalidMediaException("Lỗi khi thực hiện lưu danh sách media: " + message);
    }

    public static InvalidMediaException uploadFailed(String message) {
        return new InvalidMediaException("Lỗi khi thực hiện tải lên media: " + message);
    }

    public static InvalidMediaException moveFailed(String message) {
        return new InvalidMediaException("Lỗi khi thực hiện di chuyển media: " + message);
    }

}
