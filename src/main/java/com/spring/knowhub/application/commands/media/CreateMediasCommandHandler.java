package com.spring.knowhub.application.commands.media;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.media.CreateMediaValidator;
import com.spring.knowhub.domain.enums.media.MediaStatus;
import com.spring.knowhub.domain.enums.media.MediaType;
import com.spring.knowhub.domain.exceptions.media.InvalidMediaException;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.repositories.media.MediaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Transactional
public class CreateMediasCommandHandler implements CommandHandler<CreateMediasCommand , List<Long>> {

    private final MediaRepository mediaRepository ;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateMediasCommand ;
    }

    @Override
    public List<Long> handle(CreateMediasCommand commands) {
        for (CreateMediaCommand command : commands.getMedias()) {
            CreateMediaValidator.validate(command);
        }
        List<Media> medias = commands.getMedias().stream()
                .map(item -> {
                    if (mediaRepository.existsByUrl(item.getUrl())) {
                        throw InvalidMediaException.duplicateUrl(item.getUrl());
                    }

                    return new Media(
                            null,
                            item.getPublicId(),
                            item.getUrl(),
                            item.getOriginalName(),
                            item.getFormat(),
                            item.getSize(),
                            item.getType() != null
                                    ? MediaType.valueOf(item.getType())
                                    : null,
                            item.getFolder(),
                            null,
                            null,
                            MediaStatus.TEMP
                    );
                })
                .toList();

        return mediaRepository.saveAll(medias)
                .stream()
                .map(Media::getId)
                .toList();
    }
}
