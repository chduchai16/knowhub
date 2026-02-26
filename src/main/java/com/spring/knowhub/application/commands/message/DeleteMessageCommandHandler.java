package com.spring.knowhub.application.commands.message;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.message.DeleteMessageValidator;
import com.spring.knowhub.domain.exceptions.message.MessageNotFoundException;
import com.spring.knowhub.domain.exceptions.message.UnauthorizedMessageAccessException;
import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.repositories.message.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class DeleteMessageCommandHandler implements CommandHandler<DeleteMessageCommand, Void> {

    private final MessageRepository messageRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof DeleteMessageCommand;
    }

    @Override
    public Void handle(DeleteMessageCommand command) {
        DeleteMessageValidator.validate(command);

        Message message = messageRepository.findById(command.getMessageId())
                .orElseThrow(() -> MessageNotFoundException.withId(command.getMessageId()));

        if (!message.getSender().getId().equals(command.getUserId())) {
            throw UnauthorizedMessageAccessException.modify(command.getUserId());
        }

        message.setIsDeleted(true);
        messageRepository.save(message);

        return null;
    }
}
