package com.spring.knowhub.application.queries.user.permission;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetPagedPermissionsQueryHandler implements QueryHandler<GetPagedPermissionsQuery, Page<Permission>> {
    private final PermissionRepository permissionRepository;

    @Override
    public Page<Permission> handle(GetPagedPermissionsQuery query) {
        return permissionRepository.getPagedPermissions(PageRequest.of(query.getPage(), query.getSize()));
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetPagedPermissionsQuery;
    }
}
