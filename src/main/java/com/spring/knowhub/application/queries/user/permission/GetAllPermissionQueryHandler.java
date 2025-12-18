package com.spring.knowhub.application.queries.user.permission;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetAllPermissionQueryHandler implements QueryHandler<GetAllPermissionQueryHandler , List<Permission>> {

    private final PermissionRepository permissionRepository;;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetAllPermissionQueryHandler;
    }

    @Override
    public List<Permission> handle(GetAllPermissionQueryHandler query) {
        try {
            List<Permission> permissions = permissionRepository.findAll();
            log.info("Lấy tất cả permission thành công, tổng số: {}", permissions.size());
            return permissions;
        } catch (Exception e) {
            log.error("Lỗi khi thực hiện lấy tất cả permission", e);
            throw new RuntimeException("Lỗi không mong muốn: " + e.getMessage(), e);
        }
    }
}
