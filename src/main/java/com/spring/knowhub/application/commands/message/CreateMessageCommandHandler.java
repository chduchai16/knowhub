package com.spring.knowhub.application.commands.message;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.message.CreateMessageValidator;
import com.spring.knowhub.domain.enums.media.OwnerType;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.media.MediaRepository;
import com.spring.knowhub.domain.repositories.message.MessageRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class CreateMessageCommandHandler implements CommandHandler<CreateMessageCommand, Long> {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateMessageCommand;
    }

    @Override
    public Long handle(CreateMessageCommand command) {
        CreateMessageValidator.validate(command);

        User sender = userRepository.findById(command.getSenderId())
                .orElseThrow(() -> UserNotFoundException.byId(command.getSenderId()));

        User receiver = userRepository.findById(command.getReceiverId())
                .orElseThrow(() -> UserNotFoundException.byId(command.getReceiverId()));

        List<Media> medias = null;
        if (command.getMediaIds() != null && !command.getMediaIds().isEmpty()) {
            medias = mediaRepository.findAllById(command.getMediaIds());
        }

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(command.getContent());
        message.setIsDeleted(false);
        message.setMedia(medias);

        Message savedMessage = messageRepository.save(message);

        if (medias != null && !medias.isEmpty()) {
            medias.forEach(media -> {
                media.setOwnerType(OwnerType.MESSAGE);
                media.setOwnerId(savedMessage.getId());
            });
            mediaRepository.saveAll(medias);
        }

        return savedMessage.getId();
    }
}
