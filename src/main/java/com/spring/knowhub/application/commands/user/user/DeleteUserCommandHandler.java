package com.spring.knowhub.application.commands.user.user;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.user.user.DeleteUserValidator;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.repositories.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class DeleteUserCommandHandler implements CommandHandler<DeleteUserCommand, Long> {

    private final UserRepository userRepository;

    @Override
    public Long handle(DeleteUserCommand command) {
        DeleteUserValidator.validate(command);
        Long userId = command.getUserId();
        userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.byId(userId));
        userRepository.deleteById(userId);
        return userId;
    }

    @Override
    public boolean supports(Object command) {
        return command instanceof DeleteUserCommand;
    }
}
