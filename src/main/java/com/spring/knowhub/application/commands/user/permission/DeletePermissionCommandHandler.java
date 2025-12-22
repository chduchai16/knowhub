package com.spring.knowhub.application.commands.user.permission;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.user.permission.DeletePermissionValidator;
import com.spring.knowhub.domain.exceptions.user.permission.PermissionNotFoundException;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePermissionCommandHandler implements CommandHandler<DeletePermissionCommand, Long> {

    private final PermissionRepository permissionRepository;

    @Override
    public Long handle(DeletePermissionCommand command) {
        DeletePermissionValidator.validate(command);
        Long permissionId = command.getId();
        permissionRepository.findById(permissionId)
                .orElseThrow(() ->
                        PermissionNotFoundException.permissionNotFoundById(permissionId)
                );
        permissionRepository.deleteById(permissionId);
        return permissionId;
    }

    @Override
    public boolean supports(Object command) {
        return command instanceof DeletePermissionCommand;
    }
}

