package com.spring.knowhub.application.commands.user.role;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.user.role.DeleteRoleValidator;
import com.spring.knowhub.domain.repositories.user.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class DeleteRoleCommandHandler implements CommandHandler<DeleteRoleCommand , Void> {

    private final RoleRepository roleRepository ;

    @Override
    public boolean supports(Object command) {
        return command instanceof DeleteRoleCommand;
    }

    @Override
    public Void handle(DeleteRoleCommand command) {
        DeleteRoleValidator.validate(command.getRoleId());
        return roleRepository.deleteById(command.getRoleId()) ;
    }

}
