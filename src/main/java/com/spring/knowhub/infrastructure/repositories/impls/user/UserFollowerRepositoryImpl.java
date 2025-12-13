package com.spring.knowhub.infrastructure.repositories.impls.user;

import com.spring.knowhub.domain.models.user.UserFollow;
import com.spring.knowhub.domain.repositories.user.UserFollowRepository;

import java.util.Optional;

public class UserFollowerRepositoryImpl implements UserFollowRepository {
    @Override
    public Optional<UserFollow> save(UserFollow userFollow) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<UserFollow> findByFollowerIdAndFollowingId(Long followerId, Long followingId) {
        return Optional.empty();
    }
}
