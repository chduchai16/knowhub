package com.spring.knowhub.infrastructure.exceptions.message;

public class MessageMapperException extends RuntimeException {
    public MessageMapperException(String message) {
        super(message);
    }

    public static MessageMapperException errorMapping(String detail) {
        return new MessageMapperException("Lỗi ánh xạ tin nhắn: " + detail);
    }
}
