package com.spring.knowhub.domain.exceptions.message;

public class InvalidMessageContentException extends MessageDomainException {
    public InvalidMessageContentException(String message) {
        super("INVALID_MESSAGE_CONTENT", message);
    }
}
