package com.spring.knowhub.application.queries.post.post;

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
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetNewFeedsQueryHandler implements QueryHandler<GetNewFeedsQuery , Page<Post>> {

    private final PostRepository postRepository ;
    private final MediaRepository mediaRepository ;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetNewFeedsQuery ;
    }

    @Override
    public Page<Post> handle(GetNewFeedsQuery query) {
        Specification<Post> specification = new AlwaysTrueSpecification<>();
        specification = specification.and(PostSpecification.hasStatus("PUBLISHED")) ;
        Page<Post> pagedPost = postRepository.findPostsPaged(specification , query.getPageable());

        pagedPost.getContent().forEach(post -> {
            Specification<Media> mediaSpecification = new AlwaysTrueSpecification<>();
            mediaSpecification = mediaSpecification.and(new MediaHasOwnerIdSpec(post.getId())) ;
            mediaSpecification = mediaSpecification.and(new MediaHasOwnerTypeSpec(OwnerType.POST)) ;
            List<Media> medias = mediaRepository.findAllByOwnerIdAndOwnerType(mediaSpecification) ;
            post.setMedia(medias);
        });
        return pagedPost ;
    }
}
