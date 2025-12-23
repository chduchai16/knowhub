package com.spring.knowhub.application.commands.user.user;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserCommand implements Command<Long> {
    private Long userId;
    private String fullName;
    private String bio;
    private String avatarUrl;
    private Long roleId;
}
