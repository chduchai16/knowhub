package com.spring.knowhub.application.commands.user.user;

import java.util.Optional;

import com.spring.knowhub.application.validators.user.user.CreateUserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.exceptions.user.user.CreateUserException;
import com.spring.knowhub.domain.enums.UserStatus;
import com.spring.knowhub.domain.exceptions.user.user.DuplicateUserException;
import com.spring.knowhub.domain.exceptions.user.user.InvalidUserException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserRepositoryException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, Long> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long handle(CreateUserCommand command) {
        CreateUserValidator.validate(command);
        if (userRepository.findByUsername(command.getUsername()).isPresent()) {
            throw DuplicateUserException.usernameAlreadyExists(command.getUsername());
        }
        if (userRepository.findByEmail(command.getEmail()).isPresent()) {
            throw DuplicateUserException.emailAlreadyExists(command.getEmail());
        }
        String hashedPassword = passwordEncoder.encode(command.getPassword());
        User user = new User(
                null,
                command.getUsername(),
                command.getEmail(),
                hashedPassword,
                command.getFullName(),
                command.getBio(),
                command.getAvatarUrl(),
                UserStatus.ACTIVE,
                null
        );
        User savedUser = userRepository.save(user).orElseThrow(() -> UserRepositoryException.saveFailed("Không thể lưu user mới"));
        return savedUser.getId();
    }

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateUserCommand;
    }
}
