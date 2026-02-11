package com.spring.knowhub.application.validators.message;

import com.spring.knowhub.application.commands.message.CreateMessageCommand;
import com.spring.knowhub.domain.exceptions.message.InvalidMessageContentException;
import org.apache.commons.lang3.StringUtils;

public class CreateMessageValidator {
    public static void validate(CreateMessageCommand command) {
        if (command == null) {
            throw new InvalidMessageContentException("Command gửi tin nhắn không được null");
        }
        if (command.getSenderId() == null) {
            throw new InvalidMessageContentException("ID người gửi không được trống");
        }
        if (command.getReceiverId() == null) {
            throw new InvalidMessageContentException("ID người nhận không được trống");
        }
        if (StringUtils.isBlank(command.getContent())
                && (command.getMediaIds() == null || command.getMediaIds().isEmpty())) {
            throw new InvalidMessageContentException("Tin nhắn phải có nội dung hoặc phương tiện đi kèm");
        }
    }
}
