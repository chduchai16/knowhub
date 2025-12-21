package com.spring.knowhub.application.commands.user.permission;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.exceptions.user.permission.UpdatePermissionException;
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
public class UpdatePermissionCommandHandler implements CommandHandler<UpdatePermissionCommand , Long> {

    private final PermissionRepository permissionRepository;


    @Override
    public boolean supports(Object command) {
        return command instanceof UpdatePermissionCommand ;
    }

    @Override
    public Long handle(UpdatePermissionCommand command) {
        log.info("Bắt đầu thực hiện cập nhật permission với ID: {}", command.getId());
        try {
            validateCommand(command);
            Optional<Permission> existingPermission = permissionRepository.findById(command.getId());
            if(existingPermission.isEmpty()) {
                log.warn("Permission không tồn tại với ID: {}", command.getId());
                throw PermissionNotFoundException.permissionNotFoundById(command.getId());
            }
            Permission permissionToUpdate = existingPermission.get();
            permissionToUpdate.setCode(command.getCode());
            var updatedPermission = permissionRepository.save(permissionToUpdate);
            log.info("Cập nhật permission thành công với ID: {}", updatedPermission.get().getId());
            return updatedPermission.get().getId();
        } catch (UpdatePermissionException ex) {
            log.warn("Lỗi khi cập nhật permission: {}", ex.getMessage());
            throw ex;
        } catch (PermissionNotFoundException ex) {
            log.warn("Permission không tồn tại: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi cập nhật permission: {}", ex.getMessage(), ex);
            throw new UpdatePermissionException("Lỗi cập nhật permission : " + ex.getMessage(), ex);
        }
    }

    private void validateCommand(UpdatePermissionCommand command) {
        if(command.getId() == null) {
            throw UpdatePermissionException.missingRequiredField("id") ;
        }
        if(command.getCode() == null || command.getCode().isEmpty()) {
            throw UpdatePermissionException.missingRequiredField("code") ;
        }
    }
}
