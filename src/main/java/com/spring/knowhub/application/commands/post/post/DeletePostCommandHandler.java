package com.spring.knowhub.application.commands.post.post;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.post.post.DeletePostValidator;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePostCommandHandler implements CommandHandler<DeletePostCommand , Void> {

    private final PostRepository postRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof DeletePostCommand;
    }

    @Override
    public Void handle(DeletePostCommand command) {
        DeletePostValidator.validate(command);
        postRepository.deleteById(command.getId());
        return null;
    }
}
