package com.spring.knowhub.infrastructure.repositories.impls.user;

import com.spring.knowhub.domain.exceptions.user.userfollow.UserFollowNotFound;
import com.spring.knowhub.domain.models.user.UserFollow;
import com.spring.knowhub.domain.repositories.user.UserFollowRepository;
import com.spring.knowhub.infrastructure.entities.user.UserFollowerEntity;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserMapperException;
import com.spring.knowhub.infrastructure.exceptions.user.userfollow.UserFollowRepositoryException;
import com.spring.knowhub.infrastructure.mappers.user.UserFollowerMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.user.JpaUserFollowerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserFollowerRepositoryImpl implements UserFollowRepository {

    private final JpaUserFollowerRepository jpaUserFollowerRepository;
    private final UserFollowerMapper userFollowerMapper;

    @Override
    public Optional<UserFollow> save(UserFollow userFollow) {
        try {
            UserFollowerEntity entity = userFollowerMapper.fromDomainToEntity(userFollow);
            UserFollowerEntity savedEntity = jpaUserFollowerRepository.save(entity);
            UserFollow savedUserFollow = userFollowerMapper.fromEntityToDomain(savedEntity);
            return Optional.of(savedUserFollow);
        } catch (UserMapperException e) {
            log.error("Lỗi mapping khi lưu user follow: {}", userFollow, e);
            throw e;
        } catch (Exception e) {
            log.error("Lỗi khi lưu user follow: {}", userFollow, e);
            throw UserFollowRepositoryException.saveFailed("Lỗi khi lưu user follow");
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Xóa user follow theo ID: {}", id);
        try {
            jpaUserFollowerRepository.deleteById(id);
            log.info("User follow với ID {} đã được xóa thành công", id);
        } catch (Exception ex) {
            log.error("Lỗi khi thực hiện xóa user follow theo ID: {}", id, ex);
        }
    }

    @Override
    public long countByFollowerId(Long followerId) {
        return jpaUserFollowerRepository.countByFollowerId(followerId);
    }

    @Override
    public long countByUserId(Long userId) {
        return jpaUserFollowerRepository.countByUserId(userId);
    }

    @Override
    public Optional<UserFollow> findByUserNameAndFollowerName(String userName, String followerName) {
        log.info("Tìm user follow theo userName và followerName: {}", userName);
        try {
            UserFollowerEntity entity = jpaUserFollowerRepository
                    .findByUserUsernameAndFollowerUsername(userName, followerName)
                    .orElseThrow(() -> UserFollowNotFound.byUserAndFollower(userName, followerName));
            UserFollow userFollow = userFollowerMapper.fromEntityToDomain(entity);
            return Optional.of(userFollow);
        } catch (UserFollowNotFound e) {
            log.error("Lỗi khi tìm user follow theo userName và followerName: {}", userName, e);
            throw e;
        } catch (UserMapperException e) {
            log.error("Lỗi khi tìm user follow theo userName và followerName: {}", userName, e);
            throw e;
        } catch (Exception e) {
            log.error("Lỗi khi tìm user follow theo userName và followerName: {}", userName, e);
            throw UserFollowRepositoryException.findFailed(e.getMessage());
        }
    }
}
