package com.spring.knowhub.application.commands.user.role;

import com.spring.knowhub.application.buses.Command;
import lombok.Data;

@Data
public class DeleteRoleCommand implements Command<Void> {
    private Long roleId ;
}
