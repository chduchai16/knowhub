package com.spring.knowhub.presentation.controllers.message;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.message.CreateMessageCommand;
import com.spring.knowhub.application.commands.message.DeleteConversationCommand;
import com.spring.knowhub.application.commands.message.DeleteMessageCommand;
import com.spring.knowhub.application.commands.message.UpdateMessageCommand;
import com.spring.knowhub.application.queries.message.GetConversationQuery;
import com.spring.knowhub.application.queries.message.GetInboxQuery;
import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import com.spring.knowhub.presentation.mappers.message.MessageResponseMapper;
import com.spring.knowhub.presentation.requests.message.CreateMessageRequest;
import com.spring.knowhub.presentation.requests.message.UpdateMessageRequest;
import com.spring.knowhub.presentation.response.ApiResponse;
import com.spring.knowhub.presentation.response.PaginatedResponse;
import com.spring.knowhub.presentation.response.PaginationInfo;
import com.spring.knowhub.presentation.response.message.MessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@Slf4j
public class MessageController {

        private final CommandBus commandBus;
        private final QueryBus queryBus;
        private final MessageResponseMapper messageResponseMapper;

        @PostMapping
        public ResponseEntity<ApiResponse<?>> sendMessage(
                        @RequestBody CreateMessageRequest request,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                log.info("POST /api/messages - senderId={}, receiverId={}", userDetails.getUserId(),
                                request.getReceiverId());

                Long messageId = commandBus.execute(new CreateMessageCommand(
                                userDetails.getUserId(),
                                request.getReceiverId(),
                                request.getContent(),
                                request.getMediaIds()));

                return ResponseEntity.status(201).body(
                                new ApiResponse<>("SUCCESS", "Gửi tin nhắn thành công", messageId));
        }

        @PutMapping
        public ResponseEntity<ApiResponse<?>> updateMessage(
                        @RequestBody UpdateMessageRequest request,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                log.info("PUT /api/messages - id={}, userId={}", request.getId(), userDetails.getUserId());

                Long messageId = commandBus.execute(new UpdateMessageCommand(
                                request.getId(),
                                userDetails.getUserId(),
                                request.getContent()));

                return ResponseEntity.ok(
                                new ApiResponse<>("SUCCESS", "Cập nhật tin nhắn thành công", messageId));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<?>> deleteMessage(
                        @PathVariable Long id,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                log.info("DELETE /api/messages/{} - userId={}", id, userDetails.getUserId());

                commandBus.execute(new DeleteMessageCommand(
                                id,
                                userDetails.getUserId()));

                return ResponseEntity.ok(
                                new ApiResponse<>("SUCCESS", "Xóa tin nhắn thành công", null));
        }

        @DeleteMapping("/conversation/{contactId}")
        public ResponseEntity<ApiResponse<?>> deleteConversation(
                        @PathVariable Long contactId,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                log.info("DELETE /api/messages/conversation/{} - userId={}", contactId, userDetails.getUserId());

                commandBus.execute(new DeleteConversationCommand(
                                userDetails.getUserId(),
                                contactId));

                return ResponseEntity.ok(
                                new ApiResponse<>("SUCCESS", "Xóa cuộc hội thoại thành công", null));
        }

        @GetMapping
        public ResponseEntity<ApiResponse<?>> getConversation(
                        @RequestParam Long contactId,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "20") int limit,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                log.info("GET /api/messages - userId={}, contactId={}", userDetails.getUserId(), contactId);

                Page<Message> messagePage = queryBus.execute(new GetConversationQuery(
                                userDetails.getUserId(),
                                contactId,
                                page,
                                limit));

                Page<MessageResponse> responsePage = messagePage
                                .map(messageResponseMapper::fromMessageToMessageResponse);

                PaginationInfo paginationInfo = new PaginationInfo(
                                responsePage.getTotalElements(),
                                responsePage.getTotalPages(),
                                responsePage.getNumber(),
                                responsePage.getSize());

                PaginatedResponse<MessageResponse> paginatedResponse = new PaginatedResponse<>(
                                responsePage.getContent(),
                                paginationInfo);

                return ResponseEntity.ok(
                                new ApiResponse<>("SUCCESS", "Lấy cuộc trò chuyện thành công", paginatedResponse));
        }

        @GetMapping("/inbox")
        public ResponseEntity<ApiResponse<?>> getInbox(
                        @RequestParam(required = false) String search,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "20") int limit,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                log.info("GET /api/messages/inbox - userId={}, search='{}'", userDetails.getUserId(), search);

                Page<Message> messagePage = queryBus.execute(new GetInboxQuery(
                                userDetails.getUserId(),
                                search,
                                page,
                                limit));

                Long currentUserId = userDetails.getUserId();
                Page<MessageResponse> responsePage = messagePage
                                .map(msg -> messageResponseMapper.fromMessageToMessageResponse(msg, currentUserId));

                PaginationInfo paginationInfo = new PaginationInfo(
                                responsePage.getTotalElements(),
                                responsePage.getTotalPages(),
                                responsePage.getNumber(),
                                responsePage.getSize());

                PaginatedResponse<MessageResponse> paginatedResponse = new PaginatedResponse<>(
                                responsePage.getContent(),
                                paginationInfo);

                return ResponseEntity.ok(
                                new ApiResponse<>("SUCCESS", "Lấy danh sách inbox thành công", paginatedResponse));
        }
}
