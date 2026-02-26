package com.spring.knowhub.application.queries.message;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.repositories.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetInboxQueryHandler implements QueryHandler<GetInboxQuery, Page<Message>> {
    private final MessageRepository messageRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetInboxQuery;
    }

    @Override
    public Page<Message> handle(GetInboxQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getLimit());
        return messageRepository.findLatestMessagesPerPartner(query.getUserId(), query.getSearch(), pageable);
    }
}
