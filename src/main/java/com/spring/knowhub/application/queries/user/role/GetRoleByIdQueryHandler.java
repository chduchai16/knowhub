package com.spring.knowhub.application.queries.user.role;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.application.validators.user.role.GetRoleByIdValidator;
import com.spring.knowhub.domain.exceptions.user.role.RoleNotFoundException;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.repositories.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetRoleByIdQueryHandler implements QueryHandler<GetRoleByIdQuery, Role> {

    private final RoleRepository roleRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetRoleByIdQuery;
    }

    @Override
    public Role handle(GetRoleByIdQuery query) {
        GetRoleByIdValidator.validate(query.getRoleId());
        return roleRepository.findById(query.getRoleId())
                .orElseThrow(() -> RoleNotFoundException.byId(query.getRoleId()));
    }
}
