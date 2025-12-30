package com.spring.knowhub.application.commands.user.role;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.user.role.CreateRoleValidator;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;
import com.spring.knowhub.domain.repositories.user.RoleRepository;
import com.spring.knowhub.infrastructure.exceptions.user.role.RoleRepositoryException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Transactional
public class CreateRoleCommandHandler implements CommandHandler<CreateRoleCommand , Long> {

    private final RoleRepository roleRepository ;
    private final PermissionRepository permissionRepository ;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateRoleCommand;
    }

    @Override
    public Long handle(CreateRoleCommand command) {
        CreateRoleValidator.validate(command.getName());
        Set<Permission> permissions = permissionRepository.findByIds(command.getPermissionIds());
        Role role = new Role(
                null ,
                command.getName() ,
                permissions
        );
        Role savedRole = roleRepository.save(role).orElseThrow(() -> RoleRepositoryException.saveFailed("Không thể tạo vai trò mới"));
        return savedRole.getId();
    }
}
