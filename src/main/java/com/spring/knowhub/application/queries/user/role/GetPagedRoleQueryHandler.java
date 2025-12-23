package com.spring.knowhub.application.queries.user.role;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.repositories.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPagedRoleQueryHandler implements QueryHandler<GetPagedRoleQuery , Page<Role>> {

    private final RoleRepository roleRepository ;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetPagedRoleQuery;
    }

    @Override
    public Page<Role> handle(GetPagedRoleQuery query) {
        try {
            PageRequest pageRequest = PageRequest.of(query.getPage(), query.getPageSize());
            return roleRepository.findRolesPaged(pageRequest , query.getKeyword());
        } catch (Exception ex){
            throw ex ;
        }
    }
}
