package com.spring.knowhub.application.commands.user.permission;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePermissionCommand implements Command<Long> {
    private Long id;
    private String code;
}
