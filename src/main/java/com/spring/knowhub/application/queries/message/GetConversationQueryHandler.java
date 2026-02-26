package com.spring.knowhub.application.queries.message;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.repositories.message.MessageRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetConversationQueryHandler implements QueryHandler<GetConversationQuery, Page<Message>> {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetConversationQuery;
    }

    @Override
    public Page<Message> handle(GetConversationQuery query) {
        if (!userRepository.existsById(query.getContactId())) {
            throw UserNotFoundException.byId(query.getContactId());
        }

        PageRequest pageRequest = PageRequest.of(
                query.getPage(),
                query.getLimit(),
                Sort.by(Sort.Order.desc("createdAt")));

        return messageRepository.findMessagesBetweenUsers(
                query.getCurrentUserId(),
                query.getContactId(),
                pageRequest);
    }
}
