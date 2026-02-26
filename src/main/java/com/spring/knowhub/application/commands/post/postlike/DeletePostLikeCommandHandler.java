package com.spring.knowhub.application.commands.post.postlike;

import org.springframework.stereotype.Component;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.repositories.post.PostLikeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional
public class DeletePostLikeCommandHandler implements CommandHandler<DeletePostLikeCommand, Void> {

    private final PostLikeRepository postLikeRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof DeletePostLikeCommand;
    }

    @Override
    public Void handle(DeletePostLikeCommand command) {
        postLikeRepository.deleteById(command.getId());
        return null;
    }
}
