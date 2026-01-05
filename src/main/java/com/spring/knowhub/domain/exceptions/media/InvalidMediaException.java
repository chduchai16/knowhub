package com.spring.knowhub.domain.exceptions.media;


public class InvalidMediaException extends MediaDomainException {

    public InvalidMediaException(String message) {
        super("MEDIA_INVALID_ERROR", message);
    }

    public InvalidMediaException(String message, Throwable cause) {
        super("MEDIA_INVALID_ERROR", message, cause);
    }

    public static InvalidMediaException duplicateUrl(String url) {
        return new InvalidMediaException("Media với URL '" + url + "' đã tồn tại.");
    }

    public static InvalidMediaException invalidMediaSize(Long size, Long maxSize) {
        return new InvalidMediaException("kích thước media '" + size + "' vượt quá giới hạn cho phép là '" + maxSize + "'.");
    }
}
