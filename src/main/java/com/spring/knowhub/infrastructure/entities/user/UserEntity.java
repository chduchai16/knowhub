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

    @Column(unique = true, nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String username;

    @Column(unique = true, nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String email;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String password;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
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

    private Long followerQuantity = 0L;
    private Long followingQuantity = 0L;
    private Long postQuantity = 0L;

}

