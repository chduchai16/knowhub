package com.spring.knowhub.application.commands.user.permission;

import lombok.Data;

@Data
public class UpdatePermissionCommand {
    private Long id;
    private String code;
}
