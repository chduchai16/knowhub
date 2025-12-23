package com.spring.knowhub.application.commands.user.role;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UpdateRoleCommand implements Command<Long> {
    private Long roleId ;
    private String name ;
    private Set<Long> permissionIds;
}
