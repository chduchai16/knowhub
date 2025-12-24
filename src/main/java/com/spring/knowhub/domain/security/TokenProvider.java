package com.spring.knowhub.domain.security;

public interface TokenProvider {
    String generate(String username ,Long roleId , Boolean rememberMe);
    String validateAndGetUsername(String token);
    Boolean validate (String token );
}
