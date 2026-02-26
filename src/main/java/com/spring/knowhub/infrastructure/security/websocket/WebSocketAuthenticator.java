package com.spring.knowhub.infrastructure.security.websocket;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.spring.knowhub.domain.security.TokenProvider;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import com.spring.knowhub.infrastructure.security.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketAuthenticator implements ChannelInterceptor {

    private final TokenProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        // Xử lý khi client kết nối (CONNECT command)
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            try {
                log.debug("Xác thực kết nối WebSocket");
                String token = resolveToken(accessor);

                if (token != null && tokenProvider.validate(token)) {
                    String username = tokenProvider.validateAndGetUsername(token);
                    log.info("Token hợp lệ cho username: {}", username);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // Tạo authentication token từ user details với userID làm name (getName())
                    // Phục vụ việc gửi tin nh`n realtime theo ID qua convertAndSendToUser
                    WebSocketAuthentication auth = new WebSocketAuthentication(
                            userDetails,
                            null,
                            userDetails.getAuthorities(),
                            ((CustomUserDetails) userDetails).getUserId());

                    accessor.setUser(auth);
                    log.info("WebSocket xác thực thành công! Principal name hiện tại: {}", auth.getName());
                } else {
                    log.warn("Xác thực thất bại: Token null hoặc không hợp lệ");
                }
            } catch (Exception e) {
                log.error("Lỗi xác thực kết nối WebSocket", e);
            }
        }

        return message;
    }

    // lấy token từ header / param
    private String resolveToken(StompHeaderAccessor accessor) {
        String bearerToken = accessor.getFirstNativeHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            log.info("Tìm thấy token trong Authorization header");
            return bearerToken.substring(7);
        }

        String token = accessor.getFirstNativeHeader("token");
        if (token != null && !token.isEmpty()) {
            log.info("Tìm thấy token trong 'token' header");
            return token;
        }

        log.warn("Không tìm thấy token trong bất kỳ header nào!");
        return null;
    }
}
