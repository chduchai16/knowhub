package com.spring.knowhub.domain.security;

import com.spring.knowhub.domain.models.user.User;

public interface TokenProvider {
    String generate(User user ,Boolean rememberMe);
    String validateAndGetUsername(String token);
    Boolean validate (String token );
}
