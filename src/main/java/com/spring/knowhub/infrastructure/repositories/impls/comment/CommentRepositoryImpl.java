package com.spring.knowhub.infrastructure.repositories.impls.comment;

import com.spring.knowhub.domain.exceptions.comment.CommentNotFoundException;
import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.domain.repositories.comment.CommentRepository;
import com.spring.knowhub.infrastructure.exceptions.comment.CommentMapperException;
import com.spring.knowhub.infrastructure.exceptions.comment.CommentRepositoryException;
import com.spring.knowhub.infrastructure.mappers.comment.CommentMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.comment.JpaCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comment save(Comment comment) {
        log.info("Lưu bình luận mới hoặc cập nhật bình luận với ID: {}", comment.getId());
        try {
            var entity = commentMapper.fromDomainToEntity(comment);
            var savedEntity = jpaCommentRepository.save(entity);
            Comment savedComment = commentMapper.fromEntityToDomain(savedEntity);
            log.info("Lưu bình luận thành công với ID: {}", savedComment.getId());
            return savedComment;
        } catch (CommentMapperException ex) {
            log.error("Lỗi ánh xạ bình luận khi lưu. Chi tiết: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi lưu bình luận. Chi tiết: {}", ex.getMessage());
            throw CommentRepositoryException.saveFailed(ex.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Xóa bình luận với ID: {}", id);
        try {
            jpaCommentRepository.deleteById(id);
            log.info("Xóa bình luận thành công với ID: {}", id);
        } catch (Exception ex) {
            log.error("Lỗi khi xóa bình luận với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw CommentRepositoryException.deleteFailed(ex.getMessage());
        }
    }

    @Override
    public Optional<Comment> findById(Long id) {
        log.info("Tìm bình luận với ID: {}", id);
        try {
            Optional<Comment> commentOptional = jpaCommentRepository.findById(id)
                    .map(commentMapper::fromEntityToDomain);
            if (commentOptional.isEmpty()) {
                throw CommentNotFoundException.withId(id);
            }
            log.info("Tìm thấy bình luận với ID: {}", id);
            return commentOptional;
        } catch (CommentNotFoundException ex) {
            log.warn("Không tìm thấy bình luận với ID: {}", id);
            throw ex;
        } catch (CommentMapperException ex) {
            log.error("Lỗi ánh xạ bình luận với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm bình luận với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw CommentRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Page<Comment> findCommentsPaged(Pageable pageable) {
        log.info("Tìm bình luận phân trang với thông số: {}", pageable);
        try {
            Page<Comment> commentPage = jpaCommentRepository.findAll(pageable)
                    .map(commentMapper::fromEntityToDomain);
            log.info("Tìm thấy {} bình luận phân trang", commentPage.getTotalElements());
            return commentPage;
        } catch (CommentMapperException ex) {
            log.error("Lỗi ánh xạ bình luận phân trang. Chi tiết: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm bình luận phân trang. Chi tiết: {}", ex.getMessage());
            throw CommentRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Page<Comment> findByPostIdPaged(Long postId, Pageable pageable) {
        log.info("Tìm bình luận của bài viết ID: {} với thông số: {}", postId, pageable);
        try {
            Page<Comment> commentPage = jpaCommentRepository.findByPostId(postId, pageable)
                    .map(commentMapper::fromEntityToDomain);
            log.info("Tìm thấy {} bình luận cho bài viết ID: {}", commentPage.getTotalElements(), postId);
            return commentPage;
        } catch (CommentMapperException ex) {
            log.error("Lỗi ánh xạ bình luận theo postId. Chi tiết: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm bình luận theo postId. Chi tiết: {}", ex.getMessage());
            throw CommentRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Long countByPostId(Long postId) {
        log.info("Đếm số lượng bình luận của bài viết ID: {}", postId);
        try {
            Long count = jpaCommentRepository.countByPostId(postId);
            log.info("Số lượng bình luận của bài viết ID {}: {}", postId, count);
            return count != null ? count : 0L;
        } catch (Exception ex) {
            log.error("Lỗi khi đếm số lượng bình luận cho bài viết ID: {}. Chi tiết: {}", postId, ex.getMessage(), ex);
            return 0L;
        }
    }

    @Override
    public Page<Comment> findByRootIdPaged(Long rootId, Pageable pageable) {
        log.info("Tìm bình luận theo rootId: {} với thông số: {}", rootId, pageable);
        try {
            Page<Comment> commentPage = jpaCommentRepository.findByRootId(rootId, pageable)
                    .map(commentMapper::fromEntityToDomain);
            log.info("Tìm thấy {} bình luận cho rootId: {}", commentPage.getTotalElements(), rootId);
            return commentPage;
        } catch (CommentMapperException ex) {
            log.error("Lỗi ánh xạ bình luận theo rootId. Chi tiết: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm bình luận theo rootId. Chi tiết: {}", ex.getMessage());
            throw CommentRepositoryException.findFailed(ex.getMessage());
        }
    }
}
