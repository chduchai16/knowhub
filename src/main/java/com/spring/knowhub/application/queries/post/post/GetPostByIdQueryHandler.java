package com.spring.knowhub.application.queries.post.post;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.application.validators.post.post.GetPostByIdValidator;
import com.spring.knowhub.domain.exceptions.post.post.PostNotFoundException;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPostByIdQueryHandler implements QueryHandler<GetPostByIdQuery , Post> {

    private final PostRepository postRepository ;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetPostByIdQuery;
    }

    @Override
    public Post handle(GetPostByIdQuery query) {
        GetPostByIdValidator.validate(query);
        return postRepository.findById(query.getId()).orElseThrow(() -> PostNotFoundException.withId(query.getId()));
    }
}
