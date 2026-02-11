package com.spring.knowhub.application.validators.message;

import com.spring.knowhub.application.commands.message.UpdateMessageCommand;
import com.spring.knowhub.domain.exceptions.message.InvalidMessageContentException;
import org.apache.commons.lang3.StringUtils;

public class UpdateMessageValidator {
    public static void validate(UpdateMessageCommand command) {
        if (command == null) {
            throw new InvalidMessageContentException("Command cập nhật tin nhắn không được null");
        }
        if (command.getMessageId() == null) {
            throw new InvalidMessageContentException("ID tin nhắn không được trống");
        }
        if (command.getUserId() == null) {
            throw new InvalidMessageContentException("ID người dùng không được trống");
        }
        if (StringUtils.isBlank(command.getContent())) {
            throw new InvalidMessageContentException("Nội dung tin nhắn không được để trống");
        }
    }
}
