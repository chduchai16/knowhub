package com.spring.knowhub.application.commands.user.role;

import com.spring.knowhub.application.buses.Command;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateRoleCommand implements Command<Long> {
    private Long roleId ;
    private String name ;
    private Set<Long> permissionIds;
}
