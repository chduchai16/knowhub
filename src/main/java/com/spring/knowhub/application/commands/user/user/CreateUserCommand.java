package com.spring.knowhub.application.commands.user.user;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserCommand implements Command<Long> {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String bio;
    private String avatarUrl;
}
