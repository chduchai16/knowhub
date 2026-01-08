package com.spring.knowhub.presentation.response.user;

import com.spring.knowhub.domain.enums.user.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private String backgroundUrl ;
    private String status ;
    private Long roleId ;
    private String roleName ;
    private Long followerQuantity ;
    private Long followingQuantity ;
    private Long postQuantity ;
    private Gender gender;
    private LocalDate dateOfBirth ;
    private LocalDateTime createdAt ;
    private LocalDateTime updatedAt ;
}
