package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {
    Optional<User> save(User user);
    void deleteById(Long id);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Page<User> findUsersPaged(Pageable pageable);
}
