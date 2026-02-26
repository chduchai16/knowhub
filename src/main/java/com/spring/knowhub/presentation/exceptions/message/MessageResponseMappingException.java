package com.spring.knowhub.presentation.exceptions.message;

public class MessageResponseMappingException extends RuntimeException {
    public MessageResponseMappingException(String message) {
        super(message);
    }

    public static MessageResponseMappingException objectNull() {
        return new MessageResponseMappingException("Đối tượng tin nhắn bị null");
    }

    public static MessageResponseMappingException errorMapping(Exception ex) {
        return new MessageResponseMappingException("Lỗi khi ánh xạ tin nhắn: " + ex.getMessage());
    }
}
