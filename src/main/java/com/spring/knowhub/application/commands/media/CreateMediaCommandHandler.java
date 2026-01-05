package com.spring.knowhub.application.commands.media;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.media.CreateMediaValidator;
import com.spring.knowhub.domain.enums.MediaStatus;
import com.spring.knowhub.domain.enums.MediaType;
import com.spring.knowhub.domain.exceptions.media.InvalidMediaException;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.repositories.media.MediaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class CreateMediaCommandHandler implements CommandHandler<CreateMediaCommand , Long> {

    private final MediaRepository mediaRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateMediaCommand;
    }

    @Override
    public Long handle(CreateMediaCommand command) {
        CreateMediaValidator.validate(command);

        if (mediaRepository.existsByUrl(command.getUrl())) {
            throw InvalidMediaException.duplicateUrl(command.getUrl());
        }

        Media media = new Media(
                null,
                command.getPublicId(),
                command.getUrl(),
                command.getOriginalName(),
                command.getFormat(),
                command.getSize(),
                command.getType() != null ? MediaType.valueOf(command.getType()) : null,
                command.getFolder(),
                null,// ownerId
                null,// ownerType
                MediaStatus.TEMP
        );

        return mediaRepository.save(media).getId();
    }
}
