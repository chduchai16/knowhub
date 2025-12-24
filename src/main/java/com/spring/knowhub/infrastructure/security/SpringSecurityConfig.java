package com.spring.knowhub.infrastructure.security;

import com.spring.knowhub.infrastructure.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private String admin = "ADMIN";
    private String user = "USER";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        // auth
                        .requestMatchers("/api/auth/**").permitAll()
                        // permission
                        .requestMatchers(HttpMethod.GET , "/api/permissions/**").hasRole(admin)
                        .requestMatchers(HttpMethod.POST , "/api/permissions/**").hasRole(admin)
                        .requestMatchers(HttpMethod.PUT , "/api/permissions/**").hasRole(admin)
                        .requestMatchers(HttpMethod.DELETE , "/api/permissions/**").hasRole(admin)
                        //role
                        .requestMatchers(HttpMethod.GET , "/api/roles/**").hasRole(admin)
                        .requestMatchers(HttpMethod.POST , "/api/roles/**").hasRole(admin)
                        .requestMatchers(HttpMethod.PUT , "/api/roles/**").hasRole(admin)
                        .requestMatchers(HttpMethod.DELETE , "/api/roles/**").hasRole(admin)
                        // user
                        .requestMatchers(HttpMethod.GET , "/api/users/**").hasAnyRole(admin, user)
                        .requestMatchers(HttpMethod.POST , "/api/users/**").hasRole(admin)
                        .requestMatchers(HttpMethod.PUT , "/api/users/**").hasAnyRole(admin, user)
                        .requestMatchers(HttpMethod.DELETE , "/api/users/**").hasRole(admin)

                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}