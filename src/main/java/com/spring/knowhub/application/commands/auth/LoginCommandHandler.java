package com.spring.knowhub.application.commands.auth;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.exceptions.auth.InvalidLoginException;
import com.spring.knowhub.application.validators.auth.LoginValidator;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.domain.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginCommandHandler implements CommandHandler<LoginCommand , String> {

    private final UserRepository userRepository ;
    private final TokenProvider tokenProvider ;
    private final PasswordEncoder passwordEncoder ;

    @Override
    public boolean supports(Object command) {
        return command instanceof LoginCommand;
    }

    @Override
    public String handle(LoginCommand command) {
        LoginValidator.validate(command);
        User user = userRepository.findByUsername(command.getUsername()).orElseThrow(() -> UserNotFoundException.byUsername(command.getUsername())) ;
        if(!passwordEncoder.matches(command.getPassword() , user.getPassword())){
            throw InvalidLoginException.authenticationFailed();
        }
        return tokenProvider.generate(user.getUsername() , user.getRole().getId() ,command.getRememberMe()) ;
    }
}
