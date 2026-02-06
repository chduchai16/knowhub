package com.spring.knowhub.infrastructure.repositories.impls.post;

import com.spring.knowhub.domain.models.post.PostLike;
import com.spring.knowhub.domain.repositories.post.PostLikeRepository;
import com.spring.knowhub.infrastructure.entities.post.PostLikeEntity;
import com.spring.knowhub.infrastructure.exceptions.post.postlike.PostLikeMappingException;
import com.spring.knowhub.infrastructure.exceptions.post.postlike.PostLikeRepositoryException;
import com.spring.knowhub.infrastructure.mappers.post.PostLikeMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.post.JpaPostLikeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostLikeRepositoryImpl implements PostLikeRepository {

    private final JpaPostLikeRepository jpaPostLikeRepository;
    private final PostLikeMapper postLikeMapper;

    @Override
    public PostLike save(PostLike postLike) {
        log.info("Lưu hoặc cập nhật PostLike với userId: {} và postId: {}", postLike.getUser().getId(),
                postLike.getPost().getId());
        try {
            PostLikeEntity entity = postLikeMapper.fromDomainToEntity(postLike);
            PostLikeEntity savedEntity = jpaPostLikeRepository.save(entity);
            PostLike savedPostLike = postLikeMapper.fromEntityToDomain(savedEntity);
            log.info("Lưu PostLike thành công với userId: {} và postId: {}", postLike.getUser().getId(),
                    postLike.getPost().getId());
            return savedPostLike;
        } catch (PostLikeMappingException ex) {
            log.error("Lỗi ánh xạ PostLike với userId: {} và postId: {}. Chi tiết: {}", postLike.getUser().getId(),
                    postLike.getPost().getId(), ex.getMessage());
            throw ex;
        } catch (Exception e) {
            log.error("Lỗi khi lưu PostLike với userId: {} và postId: {}. Chi tiết: {}", postLike.getUser().getId(),
                    postLike.getPost().getId(), e.getMessage());
            throw PostLikeRepositoryException.saveFailed(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Xóa PostLike với ID: {}", id);
        try {
            jpaPostLikeRepository.deleteById(id);
            log.info("Xóa PostLike thành công với ID: {}", id);
        } catch (Exception e) {
            log.error("Lỗi khi xóa PostLike với ID: {}. Chi tiết: {}", id, e.getMessage());
            throw PostLikeRepositoryException.deleteFailed(e.getMessage());
        }
    }

    @Override
    public List<PostLike> findPostLikesByPostId(Long postId) {
        log.info("Tìm PostLikes với postId: {}", postId);
        try {
            List<PostLikeEntity> entities = jpaPostLikeRepository.findByPostId(postId);
            List<PostLike> postLikes = entities.stream().map(postLikeMapper::fromEntityToDomain).toList();
            log.info("Tìm thấy {} PostLikes với postId: {}", postLikes.size(), postId);
            return postLikes;
        } catch (PostLikeMappingException ex) {
            log.error("Lỗi ánh xạ PostLike với postId: {}. Chi tiết: {}", postId, ex.getMessage());
            throw ex;
        } catch (Exception e) {
            log.error("Lỗi khi tìm PostLikes với postId: {}. Chi tiết: {}", postId, e.getMessage());
            throw PostLikeRepositoryException.findFailed(e.getMessage());
        }
    }

    @Override
    public Long countByPostId(Long postId) {
        log.info("Đếm PostLikes với postId: {}", postId);
        try {
            Long count = jpaPostLikeRepository.countByPostId(postId);
            log.info("Đếm được {} PostLikes với postId: {}", count, postId);
            return count;
        } catch (Exception e) {
            log.error("Lỗi khi đếm PostLikes với postId: {}. Chi tiết: {}", postId, e.getMessage());
            throw PostLikeRepositoryException.findFailed(e.getMessage());
        }
    }

    @Override
    public Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId) {
        log.info("Tìm PostLike với postId: {} và userId: {}", postId, userId);
        try {
            return jpaPostLikeRepository.findByPostIdAndUserId(postId, userId)
                    .map(postLikeMapper::fromEntityToDomain);
        } catch (Exception e) {
            log.error("Lỗi khi tìm PostLike với postId: {} và userId: {}. Chi tiết: {}", postId, userId,
                    e.getMessage());
            throw PostLikeRepositoryException.findFailed(e.getMessage());
        }
    }
}
