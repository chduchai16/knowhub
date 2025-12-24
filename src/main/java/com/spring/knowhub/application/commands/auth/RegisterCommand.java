package com.spring.knowhub.application.commands.auth;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommand implements Command<Long> {
    private String username;
    private String email;
    private String password;
}
