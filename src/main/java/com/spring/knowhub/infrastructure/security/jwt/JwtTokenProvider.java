package com.spring.knowhub.infrastructure.security.jwt;

import com.spring.knowhub.domain.security.TokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final String secretKey = "Y2h1ZHVjaGFpc2luaG5nYXltdW9pY2hpbnRoYW5nbW90bmFtaGFpbmdoaW5raG9uZ3RyYW1sZWJvbg==" ;

    @Override
    public String generate(String username , Long roleId , Boolean rememberMe) {
        long expirationMillis = rememberMe ? 7L * 24 * 60 * 60 * 1000  : 3L * 60 * 60 * 1000;  // 7 ngày hoặc 3 giờ

        Map<String, Object> claims = new HashMap<>();
        claims.put("role_id", roleId);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String validateAndGetUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validate (String token ) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
