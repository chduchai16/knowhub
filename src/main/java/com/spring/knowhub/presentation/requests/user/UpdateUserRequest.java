package com.spring.knowhub.presentation.requests.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.knowhub.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Long id ;
    private String fullName;
    private String bio;
    private String avatarUrl;
    private Long roleId;
    private Gender gender ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth ;
}
