package com.spring.knowhub.application.commands.message;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.repositories.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class DeleteConversationCommandHandler implements CommandHandler<DeleteConversationCommand, Void> {

    private final MessageRepository messageRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof DeleteConversationCommand;
    }

    @Override
    public Void handle(DeleteConversationCommand command) {
        messageRepository.deleteConversation(command.getCurrentUserId(), command.getContactId());
        return null;
    }
}
