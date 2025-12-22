package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.validators.user.user.GetUserByIdValidator;
import org.springframework.stereotype.Component;
import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetUserByIdQueryHandler implements QueryHandler<GetUserByIdQuery, User> {

    private final UserRepository userRepository;

    @Override
    public User handle(GetUserByIdQuery query) {
        GetUserByIdValidator.validate(query);
        return userRepository.findById(query.getUserId())
                .orElseThrow(() -> UserNotFoundException.byId(query.getUserId()));
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUserByIdQuery;
    }
}

