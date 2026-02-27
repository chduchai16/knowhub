package com.spring.knowhub.application.queries.message;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.enums.media.OwnerType;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.repositories.media.MediaRepository;
import com.spring.knowhub.domain.repositories.message.MessageRepository;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerIdsSpec;
import com.spring.knowhub.domain.specifications.media.MediaHasOwnerTypeSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetInboxQueryHandler implements QueryHandler<GetInboxQuery, Page<Message>> {
    private final MessageRepository messageRepository;
    private final MediaRepository mediaRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetInboxQuery;
    }

    @Override
    public Page<Message> handle(GetInboxQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getLimit());
        Page<Message> messagePage = messageRepository.findLatestMessagesPerPartner(query.getUserId(), query.getSearch(),
                pageable);

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
