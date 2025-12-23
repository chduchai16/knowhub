package com.spring.knowhub.presentation.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id ;
    private String username ;
    private String email ;
    private String fullName ;
    private String bio ;
    private String avatarUrl ;
    private String status ;
    private Long roleId ;
    private String roleName ;
}
