package com.spring.knowhub.application.commands.user.permission;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.user.permission.UpdatePermissionValidator;
import com.spring.knowhub.domain.exceptions.user.permission.PermissionNotFoundException;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;
import com.spring.knowhub.infrastructure.exceptions.user.permission.PermissionRepositoryException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class UpdatePermissionCommandHandler implements CommandHandler<UpdatePermissionCommand, Long> {

    private final PermissionRepository permissionRepository;

    @Override
    public Long handle(UpdatePermissionCommand command) {
        UpdatePermissionValidator.validate(command);
        Long permissionId = command.getId();
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() ->
                        PermissionNotFoundException.permissionNotFoundById(permissionId)
                );
        permission.setCode(command.getCode());
        return permissionRepository.save(permission)
                .orElseThrow(() ->
                        PermissionRepositoryException.saveFailed("Không thể cập nhật quyền")
                )
                .getId();
    }

    @Override
    public boolean supports(Object command) {
        return command instanceof UpdatePermissionCommand;
    }
}

