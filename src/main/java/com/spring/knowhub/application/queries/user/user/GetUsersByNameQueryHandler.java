package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;
import com.spring.knowhub.domain.specifications.user.UserHasNameSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUsersByNameQueryHandler implements QueryHandler<GetUsersByNameQuery , List<User>> {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUsersByNameQuery;
    }

    @Override
    public List<User> handle(GetUsersByNameQuery query) {
        Specification specification = new AlwaysTrueSpecification() ;
        specification = specification.and(new UserHasNameSpec(query.getName())) ;
        return userRepository.findUsersPaged(specification , query.getPageable()).getContent() ;
    }
}
