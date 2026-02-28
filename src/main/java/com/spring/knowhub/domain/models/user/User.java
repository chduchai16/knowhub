package com.spring.knowhub.domain.models.user;

import com.spring.knowhub.domain.enums.user.Gender;
import com.spring.knowhub.domain.enums.user.UserStatus;
import com.spring.knowhub.domain.models.BaseModel;

import java.time.LocalDate;

public class User extends BaseModel {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String bio;
    private String avatarUrl;
    private String backgroundUrl;
    private Long followerQuantity;
    private Long followingQuantity;
    private Long postQuantity;
    private Boolean isFollowing;
    private UserStatus status;
    private Role role;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String provider; // null = local, "GOOGLE" = OAuth2

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public User(Long id, String username, String email, String password, String fullName, String bio, String avatarUrl,
            String backgroundUrl, UserStatus status, Role role, Gender gender, LocalDate dateOfBirth) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.bio = bio;
        this.avatarUrl = avatarUrl;
        this.backgroundUrl = backgroundUrl;
        this.status = status;
        this.role = role;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public Long getFollowerQuantity() {
        return followerQuantity;
    }

    public void setFollowerQuantity(Long followerQuantity) {
        this.followerQuantity = followerQuantity;
    }

    public Long getFollowingQuantity() {
        return followingQuantity;
    }

    public void setFollowingQuantity(Long followingQuantity) {
        this.followingQuantity = followingQuantity;
    }

    public Long getPostQuantity() {
        return postQuantity;
    }

    public void setPostQuantity(Long postQuantity) {
        this.postQuantity = postQuantity;
    }

    public Boolean getIsFollowing() {
        return isFollowing;
    }

    public void setIsFollowing(Boolean isFollowing) {
        this.isFollowing = isFollowing;
    }
}
