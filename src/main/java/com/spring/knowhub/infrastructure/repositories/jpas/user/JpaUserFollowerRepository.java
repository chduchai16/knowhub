package com.spring.knowhub.infrastructure.repositories.jpas.user;

import com.spring.knowhub.infrastructure.entities.user.UserFollowerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserFollowerRepository extends JpaRepository<UserFollowerEntity, Long> {
    long countByFollowerId(Long followerId);

    long countByUserId(Long userId);

    Optional<UserFollowerEntity> findByUserUsernameAndFollowerUsername(String username, String followerUsername);

    boolean existsByUserIdAndFollowerId(Long userId, Long followerId);
}
