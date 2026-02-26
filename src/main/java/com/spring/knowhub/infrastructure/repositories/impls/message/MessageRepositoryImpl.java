package com.spring.knowhub.infrastructure.repositories.impls.message;

import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.repositories.message.MessageRepository;
import com.spring.knowhub.domain.exceptions.message.MessageNotFoundException;
import com.spring.knowhub.infrastructure.entities.message.MessageEntity;
import com.spring.knowhub.infrastructure.exceptions.message.MessageMapperException;
import com.spring.knowhub.infrastructure.exceptions.message.MessageRepositoryException;
import com.spring.knowhub.infrastructure.mappers.message.MessageMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.message.JpaMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MessageRepositoryImpl implements MessageRepository {
    private final JpaMessageRepository jpaMessageRepository;
    private final MessageMapper messageMapper;

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<Message> findById(Long id) {
        log.info("Tìm tin nhắn với ID: {}", id);
        try {
            Optional<Message> messageOptional = jpaMessageRepository.findByIdWithUsers(id)
                    .map(messageMapper::fromEntityToDomain);
            if (messageOptional.isEmpty()) {
                throw MessageNotFoundException.withId(id);
            }
            log.info("Tìm thấy tin nhắn với ID: {}", id);
            return messageOptional;
        } catch (MessageNotFoundException ex) {
            log.warn("Không tìm thấy tin nhắn với ID: {}", id);
            throw ex;
        } catch (MessageMapperException ex) {
            log.error("Lỗi ánh xạ tin nhắn với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm tin nhắn với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw MessageRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Page<Message> findMessagesBetweenUsers(Long userId1, Long userId2, Pageable pageable) {
        log.info("Tìm tin nhắn giữa người dùng {} và {} phân trang: {}", userId1, userId2, pageable);
        try {
            Page<Message> messagePage = jpaMessageRepository.findConversation(userId1, userId2, pageable)
                    .map(messageMapper::fromEntityToDomain);
            log.info("Tìm thấy {} tin nhắn", messagePage.getTotalElements());
            return messagePage;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm cuộc trò chuyện. Chi tiết: {}", ex.getMessage());
            throw MessageRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Message save(Message message) {
        log.info("Lưu tin nhắn mới hoặc cập nhật ID: {}", message.getId());
        try {
            MessageEntity entity = messageMapper.fromDomainToEntity(message);
            MessageEntity savedEntity = jpaMessageRepository.save(entity);
            Message savedPost = messageMapper.fromEntityToDomain(savedEntity);
            log.info("Lưu tin nhắn thành công với ID: {}", savedPost.getId());
            return savedPost;
        } catch (Exception ex) {
            log.error("Lỗi khi lưu tin nhắn. Chi tiết: {}", ex.getMessage());
            throw MessageRepositoryException.saveFailed(ex.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Xóa tin nhắn với ID: {}", id);
        try {
            jpaMessageRepository.deleteById(id);
            log.info("Xóa tin nhắn thành công với ID: {}", id);
        } catch (Exception ex) {
            log.error("Lỗi khi xóa tin nhắn với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw MessageRepositoryException.deleteFailed(ex.getMessage());
        }
    }
}
