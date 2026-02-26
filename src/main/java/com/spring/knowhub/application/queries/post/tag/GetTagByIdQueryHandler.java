package com.spring.knowhub.application.queries.post.tag;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.application.validators.post.tag.GetTagValidator;
import com.spring.knowhub.domain.exceptions.post.tag.TagNotFoundException;
import com.spring.knowhub.domain.models.post.Tag;
import com.spring.knowhub.domain.repositories.post.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetTagByIdQueryHandler implements QueryHandler<GetTagByIdQuery, Tag> {
    private final TagRepository tagRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetTagByIdQuery;
    }

    @Override
    public Tag handle(GetTagByIdQuery query) {
        GetTagValidator.validate(query);
        Tag existingTag = tagRepository.findById(query.getId())
                .orElseThrow(() -> TagNotFoundException.byId(query.getId()));
        return existingTag;
    }
}
