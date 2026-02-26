package com.spring.knowhub.infrastructure.repositories.jpas.message;

import com.spring.knowhub.infrastructure.entities.message.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMessageRepository extends JpaRepository<MessageEntity, Long> {

        @Query("SELECT m FROM MessageEntity m WHERE " +
                        "(m.sender.id = :userId1 AND m.receiver.id = :userId2) OR " +
                        "(m.sender.id = :userId2 AND m.receiver.id = :userId1)")
        Page<MessageEntity> findConversation(@Param("userId1") Long userId1, @Param("userId2") Long userId2,
                        Pageable pageable);

        @Query("SELECT m FROM MessageEntity m " +
                        "LEFT JOIN FETCH m.sender " +
                        "LEFT JOIN FETCH m.receiver " +
                        "WHERE m.id = :id")
        java.util.Optional<MessageEntity> findByIdWithUsers(@Param("id") Long id);
}
