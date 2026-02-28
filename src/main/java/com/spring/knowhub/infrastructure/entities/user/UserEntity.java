package com.spring.knowhub.infrastructure.entities.user;

import com.spring.knowhub.domain.enums.user.Gender;
import com.spring.knowhub.infrastructure.entities.BaseEntity;
import com.spring.knowhub.domain.enums.user.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false, length = 255, columnDefinition = "NVARCHAR(255)")
    private String username;

    @Column(unique = true, nullable = false, length = 255, columnDefinition = "NVARCHAR(255)")
    private String email;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String password;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String fullName;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String bio;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String avatarUrl;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String backgroundUrl;

    private Gender gender;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Column(columnDefinition = "NVARCHAR(50)")
    private String provider;

}
