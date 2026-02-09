package com.spring.knowhub.application.queries.comment;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.domain.repositories.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetCommentsByRootIdQueryHandler implements QueryHandler<GetCommentsByRootIdQuery, Page<Comment>> {

    private final CommentRepository commentRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetCommentsByRootIdQuery;
    }

    @Override
    public Page<Comment> handle(GetCommentsByRootIdQuery query) {
        log.info("Lấy comments theo rootId: {}, page: {}, size: {}", query.getRootId(), query.getPage(),
                query.getSize());
        return commentRepository.findByRootIdPaged(
                query.getRootId(),
                PageRequest.of(query.getPage(), query.getSize()));
    }
}
