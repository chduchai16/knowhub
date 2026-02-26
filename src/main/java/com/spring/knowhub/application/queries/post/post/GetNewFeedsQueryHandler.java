package com.spring.knowhub.application.queries.post.post;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.enums.media.OwnerType;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.post.PostLike;
import com.spring.knowhub.domain.repositories.comment.CommentRepository;
import com.spring.knowhub.domain.repositories.media.MediaRepository;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.repositories.post.PostLikeRepository;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerIdSpec;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerTypeSpec;
import com.spring.knowhub.domain.specifications.post.PostSpecification;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetNewFeedsQueryHandler implements QueryHandler<GetNewFeedsQuery, Page<Post>> {

    private final PostRepository postRepository;
    private final MediaRepository mediaRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetNewFeedsQuery;
    }

    @Override
    public Page<Post> handle(GetNewFeedsQuery query) {
        Specification<Post> specification = new AlwaysTrueSpecification<>();
        specification = specification.and(PostSpecification.hasStatus("PUBLISHED"));
        Page<Post> pagedPost = postRepository.findPostsPaged(specification, query.getPageable());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentUserId = (authentication != null
                && authentication.getPrincipal() instanceof CustomUserDetails userDetails)
                        ? userDetails.getUserId()
                        : null;

        pagedPost.getContent().forEach(post -> {
            Specification<Media> mediaSpecification = new AlwaysTrueSpecification<>();
            mediaSpecification = mediaSpecification.and(new MediaHasOwnerIdSpec(post.getId()));
            mediaSpecification = mediaSpecification.and(new MediaHasOwnerTypeSpec(OwnerType.POST));
            List<Media> medias = mediaRepository.findAllByOwnerIdAndOwnerType(mediaSpecification);
            post.setMedia(medias);
            enrichPost(post, currentUserId);
        });
        return pagedPost;
    }

    private void enrichPost(Post post, Long currentUserId) {
        log.debug("Enriching post ID: {} with comment and like counts", post.getId());

        try {
            Long commentCount = commentRepository.countByPostId(post.getId());
            log.debug("Comment count for post {}: {}", post.getId(), commentCount);
            post.setCommentQuantity(commentCount != null ? commentCount : 0L);
        } catch (Exception e) {
            log.error("Error counting comments for post {}: {}", post.getId(), e.getMessage(), e);
            post.setCommentQuantity(0L);
        }

        try {
            Long likeCount = postLikeRepository.countByPostId(post.getId());
            log.debug("Like count for post {}: {}", post.getId(), likeCount);
            post.setLikeQuantity(likeCount != null ? likeCount : 0L);
        } catch (Exception e) {
            log.error("Error counting likes for post {}: {}", post.getId(), e.getMessage(), e);
            post.setLikeQuantity(0L);
        }

        if (currentUserId != null) {
            Optional<PostLike> postLike = postLikeRepository.findByPostIdAndUserId(post.getId(), currentUserId);
            if (postLike.isPresent()) {
                post.setIsLiked(true);
                post.setPostLikeId(postLike.get().getId());
            } else {
                post.setIsLiked(false);
            }
        } else {
            post.setIsLiked(false);
        }
    }
}
