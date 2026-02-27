package com.spring.knowhub.domain.repositories.message;

import com.spring.knowhub.domain.models.message.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MessageRepository {
    Optional<Message> findById(Long id);

    Page<Message> findMessagesBetweenUsers(Long userId1, Long userId2, Pageable pageable);

    Page<Message> findLatestMessagesPerPartner(Long userId, String search, Pageable pageable);

    Message save(Message message);

    void deleteById(Long id);

    void deleteConversation(Long userId1, Long userId2);
}
