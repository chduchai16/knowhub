package com.spring.knowhub.application.commands.user.role;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.user.role.UpdateRoleValidator;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;
import com.spring.knowhub.domain.repositories.user.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class UpdateRoleCommandHandler implements CommandHandler<UpdateRoleCommand, Long> {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof UpdateRoleCommand;
    }

    @Override
    public Long handle(UpdateRoleCommand command) {
        UpdateRoleValidator.validate(command);
        Role role = new Role(
                command.getRoleId(),
                command.getName(),
                permissionRepository.findByIds(command.getPermissionIds()));
        return roleRepository.save(role).getId();
    }
}
