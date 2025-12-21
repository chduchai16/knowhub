package com.spring.knowhub.application.queries.user.permission;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.exceptions.user.permission.PermissionNotFoundException;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetPermissionByIdQueryHandler implements QueryHandler<GetPermissionByIdQuery , Permission> {

    private final PermissionRepository permissionRepository; ;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetPermissionByIdQuery;
    }

    @Override
    public Permission handle(GetPermissionByIdQuery query) {
        log.info("Bắt đầu thực hiện lấy permission với id: {}", query.getId());
        try {
            validateQuery(query);
            Optional<Permission> permissionOpt = permissionRepository.findById(query.getId());
            if (permissionOpt.isEmpty()) {
                log.error("Permission không tồn tại với id: {}", query.getId());
                throw PermissionNotFoundException.permissionNotFoundById(query.getId());
            }
            log.info("Lấy permission thành công với id: {}", query.getId());
            return permissionOpt.get();
        } catch (PermissionNotFoundException ex) {
            log.warn("Permission không tồn tại: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi thực hiện lấy permission theo id", ex);
            throw new RuntimeException("Lỗi không mong muốn: " + ex.getMessage(), ex);
        }
    }

    private void validateQuery(GetPermissionByIdQuery query) {
        if(query.getId() == null || query.getId() <= 0) {
            throw new IllegalArgumentException("Id permission không hợp lệ");
        }
    }
}
