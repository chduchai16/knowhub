package com.spring.knowhub.presentation.requests.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Long userId ;
    private String fullName;
    private String bio;
    private String avatarUrl;
    private Long roleId;
}
