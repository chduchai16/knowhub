package com.spring.knowhub.application.queries.post.tag;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.post.Tag;
import com.spring.knowhub.domain.repositories.post.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetTagPagedQueryHandler implements QueryHandler<GetTagPagedQuery, Page<Tag>> {

    private final TagRepository tagRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetTagPagedQuery;
    }

    @Override
    public Page<Tag> handle(GetTagPagedQuery query) {
        return tagRepository.findTagsPaged(query.getPageable());
    }
}
