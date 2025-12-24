package com.spring.knowhub.application.commands.auth;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.auth.RegisterValidator;
import com.spring.knowhub.domain.exceptions.user.role.RoleNotFoundException;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.RoleRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterCommandHandler implements CommandHandler<RegisterCommand , Void> {

    private final UserRepository userRepository ;
    private final RoleRepository roleRepository ;
    private final PasswordEncoder passwordEncoder ;

    @Override
    public boolean supports(Object command) {
        return command instanceof RegisterCommand ;
    }

    @Override
    public Void handle(RegisterCommand command) {
        RegisterValidator.validate(command);
        User user = new User() ;
        user.setUsername(command.getUsername());
        user.setEmail(command.getEmail());
        String encodedPassword = passwordEncoder.encode(command.getPassword());
        user.setPassword(encodedPassword);
        Role role = roleRepository.findById(2L).orElseThrow(() -> RoleNotFoundException.byId(2L)) ;
        user.setRole(role);
        userRepository.save(user);
        return null ;
    }
}
