package com.spring.knowhub.domain.exceptions.message;

public class MessageNotFoundException extends MessageDomainException {
    public MessageNotFoundException(Long id) {
        super("MESSAGE_NOT_FOUND", "Không tìm thấy tin nhắn với ID: " + id);
    }

    public static MessageNotFoundException withId(Long id) {
        return new MessageNotFoundException(id);
    }
}
