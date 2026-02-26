package com.spring.knowhub.presentation.websocket;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.commands.message.CreateMessageCommand;
import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.repositories.message.MessageRepository;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import com.spring.knowhub.presentation.mappers.message.MessageResponseMapper;
import com.spring.knowhub.presentation.response.message.MessageResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatWsController {

        private final CommandBus commandBus;
        private final SimpMessagingTemplate messagingTemplate;
        private final MessageRepository messageRepository;
        private final MessageResponseMapper messageResponseMapper;

        @MessageMapping("/chat.send")
        @org.springframework.transaction.annotation.Transactional
        public void sendMessage(ChatSocketRequest request, Principal principal) {
                log.info("ChatWsController: Nhận yêu cầu gửi tin nhắn tới receiverId={}", request.getReceiverId());

                CustomUserDetails user = (CustomUserDetails) ((org.springframework.security.authentication.UsernamePasswordAuthenticationToken) principal)
                                .getPrincipal();

                log.info("WS send message - senderId={}, receiverId={}",
                                user.getUserId(),
                                request.getReceiverId());

                Long messageId = commandBus.execute(
                                new CreateMessageCommand(
                                                user.getUserId(),
                                                request.getReceiverId(),
                                                request.getContent(),
                                                request.getMediaIds()));

                // Lấy thông tin tin nhắn vừa lưu để gửi đầy đủ cho receiver
                Message message = messageRepository.findById(messageId)
                                .orElseThrow(() -> new RuntimeException("Message not found after save"));

                MessageResponse response = messageResponseMapper.fromMessageToMessageResponse(message);

                // Gửi cho người nhận
                messagingTemplate.convertAndSendToUser(
                                request.getReceiverId().toString(),
                                "/queue/messages",
                                response);

                // Gửi ngược lại cho người gửi (để đồng bộ các thiết bị/tab khác của người gửi)
                messagingTemplate.convertAndSendToUser(
                                user.getUserId().toString(),
                                "/queue/messages",
                                response);
        }
}
