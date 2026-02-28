package com.spring.knowhub.infrastructure.security.oauth2;

import com.spring.knowhub.domain.enums.user.UserStatus;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.infrastructure.mappers.user.RoleMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.user.JpaRoleRepository;
import com.spring.knowhub.infrastructure.security.jwt.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final RoleMapper roleMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = oauthToken.getPrincipal();
        String registrationId = oauthToken.getAuthorizedClientRegistrationId(); // "google" hoặc "facebook"

        String email = null;
        String name = null;
        String provider = registrationId.toUpperCase(); // "GOOGLE" hoặc "FACEBOOK"

        if ("google".equals(registrationId)) {
            email = oAuth2User.getAttribute("email");
            name = oAuth2User.getAttribute("name");
        } else if ("facebook".equals(registrationId)) {
            email = oAuth2User.getAttribute("email");
            name = oAuth2User.getAttribute("name");
            // Facebook đôi khi không trả email nếu user không cấp quyền
            if (email == null) {
                String facebookId = oAuth2User.getAttribute("id");
                email = facebookId + "@facebook.com"; // fallback
            }
        }

        log.info("OAuth2 login - provider={}, email={}", provider, email);

        final String finalEmail = email;
        final String finalName = name;
        final String finalProvider = provider;

        User user = userRepository.findByEmail(finalEmail).orElseGet(() -> {
            String baseUsername = finalEmail.contains("@")
                    ? finalEmail.split("@")[0]
                    : finalEmail;
            String username = userRepository.existsByUsername(baseUsername)
                    ? baseUsername + "_" + UUID.randomUUID().toString().substring(0, 6)
                    : baseUsername;

            Role defaultRole = jpaRoleRepository.findByName("USER")
                    .map(roleMapper::fromEntityToDomain)
                    .orElse(null);

            User newUser = new User();
            newUser.setEmail(finalEmail);
            newUser.setFullName(finalName != null ? finalName : username);
            newUser.setUsername(username);
            newUser.setPassword("");
            newUser.setProvider(finalProvider);
            newUser.setStatus(UserStatus.ACTIVE);
            newUser.setRole(defaultRole);

            log.info("Tạo user mới từ OAuth2 - provider={}, username={}, role={}",
                    finalProvider, username, defaultRole != null ? defaultRole.getName() : "none");
            return userRepository.save(newUser);
        });

        String token = jwtTokenProvider.generate(user, true);

        response.sendRedirect("http://localhost:3000/oauth-success?token=" + token);
    }
}
