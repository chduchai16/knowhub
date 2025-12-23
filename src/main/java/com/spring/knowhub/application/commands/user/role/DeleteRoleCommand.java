package com.spring.knowhub.application.commands.user.role;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteRoleCommand implements Command<Void> {
    private Long roleId ;
}
