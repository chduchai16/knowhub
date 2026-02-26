package com.spring.knowhub.application.queries.user.permission;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.application.validators.user.permission.GetPermissionByIdValidator;
import com.spring.knowhub.domain.exceptions.user.permission.PermissionNotFoundException;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetPermissionByIdQueryHandler
        implements QueryHandler<GetPermissionByIdQuery, Permission> {

    private final PermissionRepository permissionRepository;

    @Override
    public Permission handle(GetPermissionByIdQuery query) {
        GetPermissionByIdValidator.validate(query);

        return permissionRepository.findById(query.getId())
                .orElseThrow(() -> PermissionNotFoundException.permissionNotFoundById(query.getId()));
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetPermissionByIdQuery;
    }
}
