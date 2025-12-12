package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.UserFollow;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserFollowQueryRepository {
    Optional<UserFollow> findByFollowerIdAndFollowingId(Long followerId, Long followingId);
}
