package com.spring.knowhub.application.queries.post.post;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;
import com.spring.knowhub.domain.specifications.post.PostSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPagedPostQueryHandler implements QueryHandler<GetPagedPostQuery , Page<Post>> {

    private final PostRepository postRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetPagedPostQuery ;
    }

    @Override
    public Page<Post> handle(GetPagedPostQuery query) {
        Specification<Post> specification = new AlwaysTrueSpecification<>();
        specification.and(PostSpecification.hasStatus(query.getStatus().toUpperCase())) ;
        specification.and(PostSpecification.hasKeyword(query.getKeyword())) ;
        return postRepository.findPostsPaged(specification , query.getPageable());
    }
}
