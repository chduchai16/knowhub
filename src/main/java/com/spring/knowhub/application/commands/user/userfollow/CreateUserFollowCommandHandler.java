package com.spring.knowhub.application.commands.user.userfollow;

import org.springframework.stereotype.Component;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.models.user.UserFollow;
import com.spring.knowhub.domain.repositories.user.UserFollowRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.infrastructure.exceptions.user.userfollow.UserFollowRepositoryException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateUserFollowCommandHandler implements CommandHandler<CreateUserFollowCommand , Long > {

    private final UserFollowRepository userFollowRepository;
    private final UserRepository userRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateUserFollowCommand;
    }

    @Override
    public Long handle(CreateUserFollowCommand command) {
        User existingUser = userRepository.findById(command.getUserId()).orElseThrow(() -> UserNotFoundException.byId(command.getUserId())) ;
        User existingFollower = userRepository.findById(command.getFollowerId()).orElseThrow(() -> UserNotFoundException.byId(command.getFollowerId())) ;
        UserFollow savedUserFollow = userFollowRepository.save(new UserFollow(null, existingUser, existingFollower)).orElseThrow(() -> UserFollowRepositoryException.saveFailed("Lưu thất bại"));
        return savedUserFollow.getId();
    }
}
