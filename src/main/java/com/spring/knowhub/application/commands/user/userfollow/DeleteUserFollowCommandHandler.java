package com.spring.knowhub.application.commands.user.userfollow;

import org.springframework.stereotype.Component;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.exceptions.user.userfollow.UserFollowNotFound;
import com.spring.knowhub.domain.models.user.UserFollow;
import com.spring.knowhub.domain.repositories.user.UserFollowRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DeleteUserFollowCommandHandler implements CommandHandler<DeleteUserFollowCommand , Void> {
    
    private final UserFollowRepository userFollowRepository;
    
    @Override
    public boolean supports(Object command) {
        return command instanceof DeleteUserFollowCommand;
    }

    @Override
    public Void handle(DeleteUserFollowCommand command) {
        UserFollow userFollow = userFollowRepository.findByUserNameAndFollowerName(command.getUserName(), command.getFollowerName()).orElseThrow(() -> UserFollowNotFound.byUserAndFollower(command.getUserName(), command.getFollowerName())) ;
        userFollowRepository.deleteById(userFollow.getId());
        return null;
    }
}
