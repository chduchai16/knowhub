package com.spring.knowhub.application.commands.post.tag;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.post.tag.CreateTagValidator;
import com.spring.knowhub.domain.exceptions.post.tag.DuplicateTagException;
import com.spring.knowhub.domain.models.post.Tag;
import com.spring.knowhub.domain.repositories.post.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class CreateTagCommandHandler implements CommandHandler<CreateTagCommand, Long> {

    private final TagRepository tagRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateTagCommand;
    }

    @Override
    public Long handle(CreateTagCommand command) {
        CreateTagValidator.validate(command);
        Tag tag = new Tag(
                null,
                command.getName());
        Boolean existsByName = tagRepository.existsByName(command.getName());
        if (existsByName) {
            throw DuplicateTagException.byName(command.getName());
        }
        Tag savedTag = tagRepository.save(tag);
        return savedTag.getId();
    }

}
