package com.spring.knowhub.presentation.controllers.notification;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.notification.MarkAllNotificationsReadCommand;
import com.spring.knowhub.application.commands.notification.MarkNotificationReadCommand;
import com.spring.knowhub.application.queries.notification.GetUnreadCountQuery;
import com.spring.knowhub.application.queries.notification.GetUserNotificationsQuery;
import com.spring.knowhub.domain.models.notification.UserNotification;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import com.spring.knowhub.presentation.mappers.notification.NotificationResponseMapper;
import com.spring.knowhub.presentation.response.ApiResponse;
import com.spring.knowhub.presentation.response.PaginatedResponse;
import com.spring.knowhub.presentation.response.PaginationInfo;
import com.spring.knowhub.presentation.response.notification.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/notifications")
@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;
    private final NotificationResponseMapper notificationResponseMapper;

    // danh sách thông báo của user hiện tại
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getNotifications(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit) {
        log.info("GET /api/notifications - userId={}, page={}, limit={}", userDetails.getUserId(), page, limit);

        Page<UserNotification> result = queryBus.execute(
                new GetUserNotificationsQuery(userDetails.getUserId(), page, limit));

        List<NotificationResponse> data = result.getContent().stream()
                .map(notificationResponseMapper::fromUserNotificationToResponse)
                .collect(Collectors.toList());

        PaginatedResponse<NotificationResponse> response = new PaginatedResponse<>(
                data,
                new PaginationInfo(
                        result.getTotalElements(),
                        result.getTotalPages(),
                        result.getNumber(),
                        result.getSize()));

        return ResponseEntity.ok(
                new ApiResponse<>("SUCCESS", "Lấy danh sách thông báo thành công", response));
    }

    // số lượng thông báo chưa đọc
    @GetMapping("/unread-count")
    public ResponseEntity<ApiResponse<?>> getUnreadCount(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        log.info("GET /api/notifications/unread-count - userId={}", userDetails.getUserId());

        Long count = queryBus.execute(new GetUnreadCountQuery(userDetails.getUserId()));

        return ResponseEntity.ok(
                new ApiResponse<>("SUCCESS", "Lấy số thông báo chưa đọc thành công", count));
    }

    // đánh dấu một thông báo là đã đọc
    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<?>> markAsRead(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        log.info("PUT /api/notifications/{}/read - userId={}", id, userDetails.getUserId());

        commandBus.execute(new MarkNotificationReadCommand(id));

        return ResponseEntity.ok(
                new ApiResponse<>("SUCCESS", "Đánh dấu đã đọc thành công", null));
    }

    // đánh dấu tất cả
    @PutMapping("/read-all")
    public ResponseEntity<ApiResponse<?>> markAllAsRead(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        log.info("PUT /api/notifications/read-all - userId={}", userDetails.getUserId());

        commandBus.execute(new MarkAllNotificationsReadCommand(userDetails.getUserId()));

        return ResponseEntity.ok(
                new ApiResponse<>("SUCCESS", "Đánh dấu tất cả đã đọc thành công", null));
    }
}
