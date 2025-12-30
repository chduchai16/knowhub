package com.spring.knowhub.application.commands.user.permission;

import com.spring.knowhub.application.validators.user.permission.CreatePermissionValidator;
import com.spring.knowhub.domain.exceptions.user.permission.DuplicatePermissionException;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserRepositoryException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class CreatePermissionCommandHandler implements CommandHandler<CreatePermissionCommand, Long> {

    private final PermissionRepository permissionRepository;

    @Override
    public Long handle(CreatePermissionCommand command) {
        CreatePermissionValidator.validate(command);
        Boolean exists = permissionRepository.existsByCode(command.getCode());
        if (exists) {
            throw DuplicatePermissionException.DuplicatePermissionException(command.getCode());
        }
        Permission permission = new Permission();
        permission.setCode(command.getCode());
        return permissionRepository.save(permission)
                .orElseThrow(() ->
                        UserRepositoryException.saveFailed("Không thể tạo quyền mới")
                )
                .getId();
    }

    @Override
    public boolean supports(Object command) {
        return command instanceof CreatePermissionCommand;
    }
}
