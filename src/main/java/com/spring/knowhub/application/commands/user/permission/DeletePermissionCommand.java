package com.spring.knowhub.application.commands.user.permission;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DeletePermissionCommand implements Command<Long> {
    private Long id;
}
