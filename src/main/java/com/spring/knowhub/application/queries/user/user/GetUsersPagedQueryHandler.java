package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUsersPagedQueryHandler
        implements QueryHandler<GetUsersPagedQuery, Page<User>> {

    private final UserRepository userRepository;

    @Override
    public Page<User> handle(GetUsersPagedQuery query) {
        return userRepository.findUsersPaged(query.getPageable());
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUsersPagedQuery;
    }
}
