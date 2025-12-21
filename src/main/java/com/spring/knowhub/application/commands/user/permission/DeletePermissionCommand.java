package com.spring.knowhub.application.commands.user.permission;

import com.spring.knowhub.application.buses.Command;
import lombok.Data;

@Data
public class DeletePermissionCommand implements Command<Long> {
    private Long id;
}
