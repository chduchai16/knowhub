package com.spring.knowhub.application.queries.user.permission;

import java.util.List;

import org.springframework.stereotype.Component;
import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetAllPermissionQueryHandler implements QueryHandler<GetAllPermissionQuery, List<Permission>> {

    private final PermissionRepository permissionRepository;

    @Override
    public List<Permission> handle(GetAllPermissionQuery query) {
        return permissionRepository.findAll();
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetAllPermissionQuery;
    }
}
