package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.UserFollow;

import java.util.Optional;

public interface UserFollowRepository {
    Optional<UserFollow> save(UserFollow userFollow);
    void deleteById(Long id);
    Optional<UserFollow> findByFollowerIdAndFollowingId(Long followerId, Long followingId);

}
