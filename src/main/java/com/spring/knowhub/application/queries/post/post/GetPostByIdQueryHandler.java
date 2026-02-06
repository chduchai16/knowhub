package com.spring.knowhub.application.queries.post.post;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.application.validators.post.post.GetPostByIdValidator;
import com.spring.knowhub.domain.enums.media.OwnerType;
import com.spring.knowhub.domain.exceptions.post.post.PostNotFoundException;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.post.PostLike;
import com.spring.knowhub.domain.repositories.media.MediaRepository;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.repositories.post.PostLikeRepository;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerIdSpec;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerTypeSpec;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetPostByIdQueryHandler implements QueryHandler<GetPostByIdQuery, Post> {

    private final PostRepository postRepository;
    private final MediaRepository mediaRepository;
    private final PostLikeRepository postLikeRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetPostByIdQuery;
    }

    @Override
    public Post handle(GetPostByIdQuery query) {
        GetPostByIdValidator.validate(query);
        Specification<Media> spec = new AlwaysTrueSpecification<Media>()
                .and(new MediaHasOwnerIdSpec(query.getId()))
                .and(new MediaHasOwnerTypeSpec(OwnerType.POST));
        List<Media> medias = mediaRepository.findAllByOwnerIdAndOwnerType(spec);
        Post post = postRepository.findById(query.getId())
                .orElseThrow(() -> PostNotFoundException.withId(query.getId()));
        post.setMedia(medias);

        // Enrich with like count
        post.setLikeQuantity(postLikeRepository.countByPostId(post.getId()));

        // Check if current user liked it
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            Optional<PostLike> postLike = postLikeRepository.findByPostIdAndUserId(post.getId(),
                    userDetails.getUserId());
            if (postLike.isPresent()) {
                post.setIsLiked(true);
                post.setPostLikeId(postLike.get().getId());
            } else {
                post.setIsLiked(false);
            }
        } else {
            post.setIsLiked(false);
        }

        return post;
    }
}
