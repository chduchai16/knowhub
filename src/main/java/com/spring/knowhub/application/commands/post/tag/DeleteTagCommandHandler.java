package com.spring.knowhub.application.commands.post.tag;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.post.tag.DeleteTagValidator;
import com.spring.knowhub.domain.exceptions.post.tag.TagNotFoundException;
import com.spring.knowhub.domain.repositories.post.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class DeleteTagCommandHandler implements CommandHandler<DeleteTagCommand , Void> {
    private final TagRepository tagRepository ;


    @Override
    public boolean supports(Object command) {
        return command instanceof DeleteTagCommand;
    }

    @Override
    public Void handle(DeleteTagCommand command) {
        DeleteTagValidator.validate(command);
        tagRepository.findById(command.getId()).orElseThrow(() -> TagNotFoundException.byId(command.getId())) ;
        tagRepository.deleteById(command.getId());
        return null;
    }
}
