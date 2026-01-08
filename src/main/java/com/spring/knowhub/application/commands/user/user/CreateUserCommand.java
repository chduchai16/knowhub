package com.spring.knowhub.application.commands.user.user;

import com.spring.knowhub.application.buses.Command;
import com.spring.knowhub.domain.enums.user.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateUserCommand implements Command<Long> {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String bio;
    private String avatarUrl;
    private String backgroundUrl;
    private Long roleId ;
    private Gender gender ;
    private LocalDate dateOfBirth ;
}
