package com.spring.knowhub.presentation.response.user;

import com.spring.knowhub.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private Long followerQuantity ;
    private Long followingQuantity ;
    private Long postQuantity ;
    private Gender gender;
    private LocalDateTime dateOfBirth ;
    private LocalDateTime createdAt ;
    private LocalDateTime updatedAt ;
}
