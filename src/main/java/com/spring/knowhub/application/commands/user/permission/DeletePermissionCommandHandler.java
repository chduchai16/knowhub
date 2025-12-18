package com.spring.knowhub.application.commands.user.permission;

import com.spring.knowhub.application.buses.CommandHandler;
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
public class DeletePermissionCommandHandler implements CommandHandler<DeletePermissionCommand , Long> {
    private final PermissionRepository permissionRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof DeletePermissionCommand;
    }

    @Override
    public Long handle(DeletePermissionCommand command) {
        try {
            Optional<Permission> existingPermission = permissionRepository.findById(command.getId());
            if(existingPermission.isEmpty()) {
                log.error("Permission không tồn tại với id: {}", command.getId());
                throw PermissionNotFoundException.permissionNotFoundById(command.getId());
            }
            Long permissionId = command.getId();
            permissionRepository.deleteById(permissionId);
            log.info("Xóa permission thành công với id: {}", permissionId);
            return permissionId;
        } catch (PermissionNotFoundException ex) {
            log.warn("Permission không tồn tại: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi thực hiện xóa permission", ex);
            throw new RuntimeException("Lỗi không mong muốn: " + ex.getMessage(), ex);
        }
    }

    private void validateCommand(DeletePermissionCommand command) {
        if(command.getId() == null || command.getId() <= 0) {
            throw new IllegalArgumentException("Id permission không hợp lệ");
        }
    }
}
