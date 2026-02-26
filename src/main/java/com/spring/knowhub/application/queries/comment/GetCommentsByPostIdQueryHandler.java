package com.spring.knowhub.application.queries.comment;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.domain.repositories.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetCommentsByPostIdQueryHandler implements QueryHandler<GetCommentsByPostIdQuery, Page<Comment>> {

    private final CommentRepository commentRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetCommentsByPostIdQuery;
    }

    @Override
    public Page<Comment> handle(GetCommentsByPostIdQuery query) {
        return commentRepository.findByPostIdPaged(query.getPostId(), query.getPageable());
    }
}
