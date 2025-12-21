package com.spring.knowhub.application.commands.user.permission;

import org.springframework.stereotype.Component;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.exceptions.user.permission.CreatePermissionException;
import com.spring.knowhub.domain.exceptions.user.permission.InvalidPermissionException;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatePermissionCommandHandler implements CommandHandler<CreatePermissionCommand , Long> {
    private final PermissionRepository permissionRepository;


    @Override
    public boolean supports(Object command) {
        return command instanceof CreatePermissionCommand ;
    }

    @Override
    public Long handle(CreatePermissionCommand query) {
        log.info("Bắt đầu thực hiện tạo permission với code: {}", query.getCode());
        try {
            validateCommand(query);
            var permission = new com.spring.knowhub.domain.models.user.Permission();
            permission.setCode(query.getCode());
            var createdPermission = permissionRepository.save(permission);
            log.info("Tạo permission thành công với ID: {}", createdPermission.get().getId());
            return createdPermission.get().getId();
        } catch (InvalidPermissionException e) {
            log.error("Lỗi khi tạo permission: {}", e.getMessage());
            throw e;
        } catch (Exception ex) {
            log.error("Lỗi không xác định khi tạo permission: {}", ex.getMessage());
            throw new CreatePermissionException("Lỗi không xác định khi tạo permission: " + ex.getMessage(), ex);
        }
    }

    // kiểm tra code
    private void validateCommand(CreatePermissionCommand command) {
        if (command.getCode() == null || command.getCode().isEmpty()) {
            throw InvalidPermissionException.InvalidPermissionCodeException("code") ;
        }
    }

}
