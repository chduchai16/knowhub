package com.spring.knowhub.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.knowhub.infrastructure.security.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final CustomAccessDeniedHandler customAccessDeniedHandler;
        private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        private String admin = "ADMIN";
        private String user = "USER";

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .cors(cors -> {
                                })
                                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                // websocket
                                                .requestMatchers("/ws/**").permitAll()

                                                // auth
                                                .requestMatchers(HttpMethod.POST, "/api/auth/login/**").permitAll()

                                                // permission
                                                .requestMatchers(HttpMethod.GET, "/api/permissions/**").hasRole(admin)
                                                .requestMatchers(HttpMethod.POST, "/api/permissions/**").hasRole(admin)
                                                .requestMatchers(HttpMethod.PUT, "/api/permissions/**").hasRole(admin)
                                                .requestMatchers(HttpMethod.DELETE, "/api/permissions/**")
                                                .hasRole(admin)

                                                // role
                                                .requestMatchers(HttpMethod.GET, "/api/roles/**").hasRole(admin)
                                                .requestMatchers(HttpMethod.POST, "/api/roles/**").hasRole(admin)
                                                .requestMatchers(HttpMethod.PUT, "/api/roles/**").hasRole(admin)
                                                .requestMatchers(HttpMethod.DELETE, "/api/roles/**").hasRole(admin)

                                                // user
                                                .requestMatchers(HttpMethod.POST, "/api/users/follow/**")
                                                .authenticated()
                                                .requestMatchers(HttpMethod.DELETE, "/api/users/unfollow/**")
                                                .authenticated()
                                                .requestMatchers(HttpMethod.GET, "/api/users/**")
                                                .hasAnyRole("ADMIN", "USER")
                                                .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/api/users/**")
                                                .hasAnyRole("ADMIN", "USER")
                                                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")

                                                // media
                                                .requestMatchers(HttpMethod.POST, "/api/medias/**").authenticated()
                                                .requestMatchers(HttpMethod.DELETE, "/api/medias/**").authenticated()

                                                // tags
                                                .requestMatchers(HttpMethod.GET, "/api/tags/**").authenticated()
                                                .requestMatchers(HttpMethod.POST, "/api/tags/**").hasRole(admin)
                                                .requestMatchers(HttpMethod.PUT, "/api/tags/**").hasRole(admin)
                                                .requestMatchers(HttpMethod.DELETE, "/api/tags/**").hasRole(admin)

                                                // posts
                                                .requestMatchers(HttpMethod.GET, "/api/posts/**").authenticated()
                                                .requestMatchers(HttpMethod.POST, "/api/posts/*/likes").authenticated()
                                                .requestMatchers(HttpMethod.DELETE, "/api/posts/likes/**")
                                                .authenticated()
                                                .requestMatchers(HttpMethod.POST, "/api/posts/**").authenticated()
                                                .requestMatchers(HttpMethod.PUT, "/api/posts/**").authenticated()
                                                .requestMatchers(HttpMethod.DELETE, "/api/posts/**").authenticated()

                                                // reports
                                                .requestMatchers(HttpMethod.POST, "/api/reports/**").authenticated()
                                                .requestMatchers(HttpMethod.GET, "/api/reports/my-reports/**")
                                                .authenticated()
                                                .requestMatchers(HttpMethod.GET, "/api/reports**").hasRole(admin)
                                                .requestMatchers(HttpMethod.GET, "/api/reports/**").hasRole(admin)
                                                .requestMatchers(HttpMethod.PUT, "/api/reports/**").hasRole(admin)
                                                .requestMatchers(HttpMethod.DELETE, "/api/reports/**").hasRole(admin)

                                                // comments
                                                .requestMatchers(HttpMethod.POST, "/api/comments/**").authenticated()
                                                .requestMatchers(HttpMethod.PUT, "/api/comments/**").authenticated()
                                                .requestMatchers(HttpMethod.DELETE, "/api/comments/**").authenticated()

                                                // comment likes
                                                .requestMatchers(HttpMethod.POST, "/api/comments/*/likes")
                                                .authenticated()
                                                .requestMatchers(HttpMethod.DELETE, "/api/comments/likes/**")
                                                .authenticated()

                                                // comment replies
                                                .requestMatchers(HttpMethod.POST, "/api/comments/*/replies")
                                                .authenticated()
                                                .requestMatchers(HttpMethod.PUT, "/api/comments/replies/**")
                                                .authenticated()
                                                .requestMatchers(HttpMethod.DELETE, "/api/comments/replies/**")
                                                .authenticated()

                                                // comment reply likes
                                                .requestMatchers(HttpMethod.POST, "/api/comments/replies/*/likes")
                                                .authenticated()
                                                .requestMatchers(HttpMethod.DELETE, "/api/comments/replies/likes/**")
                                                .authenticated()

                                                // notifications
                                                .requestMatchers(HttpMethod.GET, "/api/notifications/**")
                                                .authenticated()
                                                .requestMatchers(HttpMethod.PUT, "/api/notifications/**")
                                                .authenticated()
                                                .requestMatchers(HttpMethod.DELETE, "/api/notifications/**")
                                                .authenticated()

                                                .requestMatchers("/ws/**").permitAll()
                                                .anyRequest().authenticated())
                                .exceptionHandling(ex -> ex
                                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                                .accessDeniedHandler(customAccessDeniedHandler))
                                .addFilterBefore(
                                                jwtAuthenticationFilter,
                                                UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}