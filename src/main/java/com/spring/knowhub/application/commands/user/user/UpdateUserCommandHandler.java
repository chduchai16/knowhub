package com.spring.knowhub.application.commands.user.user;


import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.user.user.UpdateUserValidator;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.repositories.user.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class UpdateUserCommandHandler implements CommandHandler<UpdateUserCommand, Long> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository ;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long handle(UpdateUserCommand command) {
        UpdateUserValidator.validate(command);
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> UserNotFoundException.byId(command.getUserId()));

        applyUpdates(user, command);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public boolean supports(Object command) {
        return command instanceof UpdateUserCommand;
    }

    private void applyUpdates(User user, UpdateUserCommand command) {
        if (command.getFullName() != null) {
            user.setFullName(command.getFullName().trim());
        }
        if (command.getBio() != null) {
            user.setBio(command.getBio());
        }
        if (command.getAvatarUrl() != null) {
            user.setAvatarUrl(command.getAvatarUrl());
        }
        if (command.getRoleId() != null) {
            Role role = roleRepository.findById(command.getRoleId()).orElseThrow(() -> new RuntimeException("Không tìm thấy role với id: " + command.getRoleId()));
            user.setRole(role);
        }
    }
}
