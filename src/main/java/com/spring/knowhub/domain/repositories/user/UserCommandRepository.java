package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.User;

import java.util.Optional;

public interface UserCommandRepository {
    Optional<User> save(User user);
    void deleteById(Long id);
}
