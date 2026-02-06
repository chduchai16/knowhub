package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.UserFollow;

import java.util.Optional;

public interface UserFollowRepository {
    UserFollow save(UserFollow userFollow);
    void deleteById(Long id);
    // đếm số lượng người mà userId đang follow
    long countByFollowerId(Long followerId);
    // đếm số lượng người đang follow userId
    long countByUserId(Long userId);
    Optional<UserFollow> findByUserNameAndFollowerName(String userName, String followerName);
    boolean existsByUserIdAndFollowerId(Long userId, Long followerId);
}
