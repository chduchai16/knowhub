package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.validators.user.user.GetUserByUsernameValidator;
import org.springframework.stereotype.Component;
import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetUserByUsernameQueryHandler implements QueryHandler<GetUserByUsernameQuery, User> {

    private final UserRepository userRepository;

    @Override
    public User handle(GetUserByUsernameQuery query) {
        GetUserByUsernameValidator.validate(query);
        return userRepository.findByUsername(query.getUsername())
                .orElseThrow(() ->
                        UserNotFoundException.byUsername(query.getUsername())
                );
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUserByUsernameQuery;
    }
}

