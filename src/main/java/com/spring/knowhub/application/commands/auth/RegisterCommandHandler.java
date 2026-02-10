package com.spring.knowhub.application.commands.auth;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.auth.RegisterValidator;
import com.spring.knowhub.domain.exceptions.user.role.RoleNotFoundException;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.RoleRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Transactional
public class RegisterCommandHandler implements CommandHandler<RegisterCommand, Long> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Object command) {
        return command instanceof RegisterCommand;
    }

    @Override
    public Long handle(RegisterCommand command) {
        RegisterValidator.validate(command);

        // Kiểm tra username đã tồn tại
        if (userRepository.findByUsername(command.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã tồn tại: " + command.getUsername());
        }

        // Kiểm tra email đã tồn tại
        if (userRepository.findByEmail(command.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại: " + command.getEmail());
        }

        User user = new User();
        user.setUsername(command.getUsername());
        user.setEmail(command.getEmail());
        String encodedPassword = passwordEncoder.encode(command.getPassword());
        user.setPassword(encodedPassword);
        Role role = roleRepository.findById(2L).orElseThrow(() -> RoleNotFoundException.byId(2L));
        user.setRole(role);
        String userName = "user_" + UUID.randomUUID().toString().substring(0, 12);
        user.setFullName(userName);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}
