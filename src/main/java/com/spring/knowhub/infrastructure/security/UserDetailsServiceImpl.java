package com.spring.knowhub.infrastructure.security;

import com.spring.knowhub.domain.enums.UserStatus;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.repositories.jpas.user.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = jpaUserRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.byUsername(username));
        List<GrantedAuthority> authorities = List.of(() -> "ROLE_" + user.getRole().getName().toUpperCase());

        return new CustomUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getStatus() == UserStatus.ACTIVE,
                authorities);
    }
}
