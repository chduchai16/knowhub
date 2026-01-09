package com.spring.knowhub.infrastructure.repositories.impls.post;

import com.spring.knowhub.domain.exceptions.post.post.PostNotFoundException;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.specifications.Specification;
import com.spring.knowhub.infrastructure.entities.post.PostEntity;
import com.spring.knowhub.infrastructure.exceptions.post.post.PostMapperException;
import com.spring.knowhub.infrastructure.exceptions.post.post.PostRepositoryException;
import com.spring.knowhub.infrastructure.mappers.post.PostMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.post.JpaPostRepository;
import com.spring.knowhub.infrastructure.repositories.specifications.post.PostJpaSpecificationAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository jpaPostRepository;
    private final PostMapper postMapper ;

    @Override
    public Optional<Post> findById(Long id) {
        log.info("Tìm bài viết với ID: {}", id);
        try {
            Optional<Post> postOptional = jpaPostRepository.findById(id).map(postMapper::fromEntityToDomain);
            if(postOptional.isEmpty()) {
                throw PostNotFoundException.withId(id);
            }
            log.info("Tìm thấy bài viết với ID: {}", id);
            return postOptional ;
        } catch (PostNotFoundException ex){
            log.warn("Không tìm thấy bài viết với ID: {}", id);
            throw ex ;
        } catch (PostMapperException ex) {
            log.error("Lỗi ánh xạ bài viết với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw ex ;
        }catch (Exception ex) {
            log.error("Lỗi khi tìm bài viết với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw  PostRepositoryException.findFailed(ex.getMessage()) ;
        }
    }

    @Override
    public Page<Post> findPostsPaged(Specification<Post> specification , Pageable pageable) {
        log.info("Tìm bài viết phân trang với thông số: {}", pageable);
        try {
            org.springframework.data.jpa.domain.Specification<PostEntity> jpaSpecification = PostJpaSpecificationAdapter.toJpaSpecification(specification) ;
            Page<Post> postPage = jpaPostRepository.findAll(jpaSpecification, pageable)
                    .map(postMapper::fromEntityToDomain);
            log.info("Tìm thấy {} bài viết phân trang với thông số: {}", postPage.getTotalElements(), pageable);
            return postPage ;
        } catch (PostMapperException ex) {
            log.error("Lỗi ánh xạ bài viết phân trang. Chi tiết: {}", ex.getMessage());
            throw ex ;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm bài viết phân trang. Chi tiết: {}", ex.getMessage());
            throw PostRepositoryException.findFailed(ex.getMessage()) ;
        }
    }

    @Override
    public Optional<Post> save(Post post) {
        log.info("Lưu bài viết mới hoặc cập nhật bài viết với ID: {}", post.getId());
        try {
            PostEntity postEntity = postMapper.fromDomainToEntity(post);
            PostEntity savedEntity = jpaPostRepository.save(postEntity);
            Post savedPost = postMapper.fromEntityToDomain(savedEntity);
            log.info("Lưu bài viết thành công với ID: {}", savedPost.getId());
            return Optional.of(savedPost);
        } catch (PostMapperException ex) {
            log.error("Lỗi ánh xạ bài viết khi lưu. Chi tiết: {}", ex.getMessage());
            throw ex ;
        }catch (Exception ex) {
            log.error("Lỗi khi lưu bài viết. Chi tiết: {}", ex.getMessage());
            throw PostRepositoryException.saveFailed(ex.getMessage()) ;
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Xóa bài viết với ID: {}", id);
        try {
            jpaPostRepository.deleteById(id);
            log.info("Xóa bài viết thành công với ID: {}", id);
        } catch (Exception ex) {
            log.error("Lỗi khi xóa bài viết với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw PostRepositoryException.deleteFailed(ex.getMessage()) ;
        }
    }
}
