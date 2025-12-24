package com.spring.knowhub.application.commands.auth;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCommand implements Command<String> {
    private String username;
    private String password;
    private Boolean rememberMe;
}
