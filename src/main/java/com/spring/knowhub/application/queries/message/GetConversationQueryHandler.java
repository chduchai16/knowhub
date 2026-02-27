package com.spring.knowhub.application.queries.message;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.enums.media.OwnerType;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.repositories.media.MediaRepository;
import com.spring.knowhub.domain.repositories.message.MessageRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerIdsSpec;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerTypeSpec;
import com.spring.knowhub.domain.specifications.Specification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetConversationQueryHandler implements QueryHandler<GetConversationQuery, Page<Message>> {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;

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

        Page<Message> messagePage = messageRepository.findMessagesBetweenUsers(
                query.getCurrentUserId(),
                query.getContactId(),
                pageRequest);

        if (!messagePage.isEmpty()) {
            List<Long> messageIds = messagePage.getContent().stream()
                    .map(Message::getId)
                    .collect(Collectors.toList());

            Specification<Media> mediaSpec = new AlwaysTrueSpecification<Media>()
                    .and(new MediaHasOwnerIdsSpec(messageIds))
                    .and(new MediaHasOwnerTypeSpec(OwnerType.MESSAGE));

            List<Media> medias = mediaRepository.findAllByOwnerIdAndOwnerType(mediaSpec);

            Map<Long, List<Media>> mediaMap = medias.stream()
                    .collect(Collectors.groupingBy(Media::getOwnerId));

            messagePage.getContent().forEach(message -> {
                message.setMedia(mediaMap.getOrDefault(message.getId(), Collections.emptyList()));
            });
        }

        return messagePage;
    }
}
