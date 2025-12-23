package com.spring.knowhub.presentation.requests.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String bio;
    private String avatarUrl;
    private Long roleId;
}
