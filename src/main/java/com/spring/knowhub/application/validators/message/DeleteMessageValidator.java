package com.spring.knowhub.application.validators.message;

import com.spring.knowhub.application.commands.message.DeleteMessageCommand;
import com.spring.knowhub.domain.exceptions.message.InvalidMessageContentException;

public class DeleteMessageValidator {
    public static void validate(DeleteMessageCommand command) {
        if (command == null) {
            throw new InvalidMessageContentException("Command xóa tin nhắn không được null");
        }
        if (command.getMessageId() == null) {
            throw new InvalidMessageContentException("ID tin nhắn không được trống");
        }
        if (command.getUserId() == null) {
            throw new InvalidMessageContentException("ID người dùng không được trống");
        }
    }
}
