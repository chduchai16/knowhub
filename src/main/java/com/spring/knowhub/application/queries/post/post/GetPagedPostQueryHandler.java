package com.spring.knowhub.application.queries.post.post;

import java.util.List;
import java.util.Optional;

import com.spring.knowhub.domain.models.post.PostLike;
import com.spring.knowhub.domain.repositories.post.PostLikeRepository;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.enums.media.OwnerType;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.repositories.media.MediaRepository;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerIdSpec;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerTypeSpec;
import com.spring.knowhub.domain.specifications.post.PostSpecification;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetPagedPostQueryHandler implements QueryHandler<GetPagedPostQuery, Page<Post>> {

    private final PostRepository postRepository;
    private final MediaRepository mediaRepository;
    private final PostLikeRepository postLikeRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetPagedPostQuery;
    }

    @Override
    public Page<Post> handle(GetPagedPostQuery query) {
        Specification<Post> specification = new AlwaysTrueSpecification<>();
        if (query.getStatus() != null) {
            specification = specification.and(PostSpecification.hasStatus(query.getStatus().toUpperCase()));
        }
        specification = specification.and(PostSpecification.hasUserName(query.getUsername()));
        specification = specification.and(PostSpecification.hasKeyword(query.getKeyword()));

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

            enrichPostWithLikes(post, currentUserId);
        });
        return pagedPost;
    }

    private void enrichPostWithLikes(Post post, Long currentUserId) {
        post.setLikeQuantity(postLikeRepository.countByPostId(post.getId()));

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
