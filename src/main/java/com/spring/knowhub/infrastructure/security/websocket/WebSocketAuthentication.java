package com.spring.knowhub.infrastructure.security.websocket;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class WebSocketAuthentication extends UsernamePasswordAuthenticationToken {
    private final String userId;

    public WebSocketAuthentication(Object principal, Object credentials,
            Collection<? extends GrantedAuthority> authorities,
            Long userId) {
        super(principal, credentials, authorities);
        this.userId = String.valueOf(userId);
    }

    @Override
    public String getName() {
        return userId;
    }
}
