package com.spring.knowhub.application.commands.post.tag;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.post.tag.UpdateTagValidator;
import com.spring.knowhub.domain.exceptions.post.tag.DuplicateTagException;
import com.spring.knowhub.domain.exceptions.post.tag.TagNotFoundException;
import com.spring.knowhub.domain.models.post.Tag;
import com.spring.knowhub.domain.repositories.post.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class UpdateTagCommandHandler implements CommandHandler<UpdateTagCommand, Long> {

    private final TagRepository tagRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof UpdateTagCommand;
    }

    @Override
    public Long handle(UpdateTagCommand command) {
        UpdateTagValidator.validate(command);
        Tag existingTag = tagRepository.findById(command.getId())
                .orElseThrow(() -> TagNotFoundException.byId(command.getId()));

        Boolean existsByName = tagRepository.existsByName(command.getName());
        if (existsByName && !existingTag.getName().equals(command.getName())) {
            throw DuplicateTagException.byName(command.getName());
        }

        Tag updateTag = new Tag(
                existingTag.getId(),
                command.getName());

        Tag savedTag = tagRepository.save(updateTag);
        return savedTag.getId();
    }
}
